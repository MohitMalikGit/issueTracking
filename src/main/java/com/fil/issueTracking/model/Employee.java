package com.fil.issueTracking.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fil.issueTracking.enums.Gender;
import com.fil.issueTracking.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails {
	
	
	@Id
	private String id;
	 @NotNull
	private String name;

	@OneToOne
	@JoinColumn(nullable = true)
	private Employee manager;
	@Email
	@Column(unique = true , nullable = false)
	private String email;
	private String phone;
	private Date dob;
	private Date doj;
	private String password;
	@OneToMany  
	@JoinColumn(name="raisedBy")
	private List<Issue> issueRaised;
	
	@OneToMany
	@JoinColumn(name="approvedBy")
	private List<Issue> approvals;
	
	
	@OneToMany
	@JoinColumn(name="assignedTo")
	private List<Issue> assignedIssue;
	
	@OneToMany
	private List<IssueType> expertise;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Override
	public String toString() {
		String output = "empName" + name + "empEmail" + email;
		return output;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorites = new ArrayList<>();
		authorites.add(new SimpleGrantedAuthority(this.role.name()));
		return authorites;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
