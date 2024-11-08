package com.fil.issueTracking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fil.issueTracking.enums.Gender;
import com.fil.issueTracking.enums.Role;
import com.fil.issueTracking.exception.ResourceNotFoundException;
import com.fil.issueTracking.exception.UserNameAndPasswordNotMatchedException;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.validator.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fil.issueTracking.model.Employee;
import com.fil.issueTracking.payLoad.AllUserApiResponse;
import com.fil.issueTracking.payLoad.AssigneeResponseDto;
import com.fil.issueTracking.payLoad.CurrentUserResponse;
import com.fil.issueTracking.payLoad.EmployeeDto;
import com.fil.issueTracking.payLoad.GetEmployeesResponse;
import com.fil.issueTracking.payLoad.LoginRequest;
import com.fil.issueTracking.payLoad.UpdateUserDetailResponse;
import com.fil.issueTracking.repo.EmployeeRepo;
import com.fil.issueTracking.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Autowired
	ModelMapper modelMapper;


	//original
	@Override
	public EmployeeDto userAuthentication(LoginRequest loginRequest) {
		String userid = loginRequest.getUserid();
		String password = loginRequest.getPassword();
		EmailValidator validator = EmailValidator.getInstance();


		if( validator.isValid(userid)) {
			Employee emp = repo.findByEmail(userid).orElseThrow(()-> new ResourceNotFoundException("User" , "Email" , userid));
			if(!emp.getPassword().equals(password)) throw new UserNameAndPasswordNotMatchedException();
			return modelMapper.map(emp, EmployeeDto.class);
		}
		else {
			Employee emp = repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User" , "id" , String.valueOf(userid) ));
			if(!emp.getPassword().equals(password)) throw new UserNameAndPasswordNotMatchedException();
			return modelMapper.map(emp, EmployeeDto.class);

		}
	}
	
	
	
	 


	@Override  
	public CurrentUserResponse findCurrentUser(String id) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
		CurrentUserResponse resp = new CurrentUserResponse(emp.getId(), emp.getName(),emp.getRole(),emp.getEmail(),emp.getDob(),emp.getDoj(),emp.getGender());
		if(emp.getManager()!=null)resp.setManagerName(emp.getManager().getName());
		return resp; 
	}
	
	
	
	@Override
	public AllUserApiResponse getAllUsers(String role,Integer pageNumber , Integer pageSize,String sortBy,String sortDir,String gender) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize ,sort);	
		Page<Employee> pageUser = repo.findAll(p);
		List<Employee> content = pageUser.getContent();
		if(!role.equals("all")) {
			content = content.stream().filter(e-> e.getRole().name().equals(role)).toList();
		}
		if(!gender.equals("all")) {
			content = content.stream().filter(e->e.getGender().name().equals(gender)).toList();
		}

		List<EmployeeDto> empDtoList = new ArrayList<>();
		for( Employee e : content) {
			EmployeeDto dto = new EmployeeDto();
			dto.setDateOfJoining(e.getDoj());
			dto.setEmail(e.getEmail());
			dto.setEmpId(e.getId());
			dto.setGender(e.getGender().name());
			if(e.getManager()!=null) dto.setManagerId(e.getManager().getId());
			dto.setName(e.getName());
			dto.setRole(e.getRole());
			empDtoList.add(dto);
		}
		AllUserApiResponse resp = new AllUserApiResponse();
		System.out.println(pageUser.getContent().size());
		resp.setContent(empDtoList);
		resp.setPageNumber(pageUser.getNumber()); 
		resp.setPageSize(pageUser.getSize());
		resp.setTotalElements(pageUser.getTotalElements());
		resp.setLastPage(pageUser.isLast()); 
		return resp; 
		
	}


	@Override 
	@Transactional
	public void changePassword(String id, String password) {
		Employee emp = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found" , "userId" , String.valueOf(id)));
		emp.setPassword(password);
		repo.save(emp);
	}






	@Override
	public List<AssigneeResponseDto> getAllAssignee() {
		List<Employee> allByRole = repo.findAllByRole(Role.support);
		List<AssigneeResponseDto> collect = allByRole.stream().map(emp-> new AssigneeResponseDto(emp.getId() , emp.getName())).collect(Collectors.toList());
		return collect;
	}






	@Override
	@Transactional
	public UpdateUserDetailResponse updateUserDetails(String empId,String role, String gender, String managerId) {
		Optional<Employee> byId = repo.findById(empId);
		Employee emp = byId.get();
		emp.setRole(Role.valueOf(role));
		emp.setManager(repo.findById(managerId).get());
		emp.setGender(Gender.valueOf(gender));
		return new UpdateUserDetailResponse(emp.getId(),emp.getName(),emp.getEmail(),emp.getRole().name(),emp.getGender().name(),emp.getDoj().toString());
	}






	@Override
	public List<GetEmployeesResponse> getAllEmployees() {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> byId = repo.findById(principal.getUsername());
		Employee emp = byId.get();
		List<Employee> content = repo.findAll().stream().filter(e-> e.getRole() == Role.employee).toList();
		if(emp.getManager() == null && emp.getRole() == Role.employee) {
			content = content.stream().filter(e->  e.getManager() == emp ).toList();
		}
		List<GetEmployeesResponse> response = new ArrayList<>();
		for( Employee e : content) {
			GetEmployeesResponse res = new GetEmployeesResponse();
			res.setId(e.getId());
			res.setName(e.getName());
			response.add(res);
		}
		return response;
	}








}
