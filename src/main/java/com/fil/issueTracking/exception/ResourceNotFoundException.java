package com.fil.issueTracking.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	int fieldId;
	public ResourceNotFoundException(String resourceName , String fieldName , int fieldId){
		super(String.format("%s not found with %s : %d", resourceName , fieldName ,fieldId ));
		this.fieldId = fieldId; 
		this.fieldName = fieldName;
		this.resourceName = resourceName;
	}
}
