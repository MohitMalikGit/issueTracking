package com.fil.issueTracking.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fil.issueTracking.payLoad.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception){
		return new ResponseEntity<>(new ApiResponse(exception.getMessage() , false ), HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler(ArgumentTypeNotMatchedException.class)
	public ResponseEntity<ApiResponse> ArgumentTypeNotMathchedExceptionHandler(ArgumentTypeNotMatchedException exception){
		return new ResponseEntity<>(new ApiResponse(exception.getMessage() , false ), HttpStatus.NOT_FOUND );
	}
	@ExceptionHandler(UserNameAndPasswordNotMatchedException.class)
	public ResponseEntity<ApiResponse> ArgumentTypeNotMathchedExceptionHandler(UserNameAndPasswordNotMatchedException exception){
		return new ResponseEntity<>(new ApiResponse(exception.getMessage( ) , false ), HttpStatus.NOT_FOUND );
	}
	
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		Map<String,String> resp = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error)-> {
			 String fieldName = ((FieldError)error).getField();
			 String message = error.getDefaultMessage();
			 resp.put(fieldName, message);
		});
		return new ResponseEntity<>(resp , HttpStatus.BAD_REQUEST);
	}
	
	
	
}
