package com.fil.issueTracking.payLoad;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllUserApiResponse {
	private List<EmployeeDto> content;
	private int pageNumber;
	private long totalElements;
	private int pageSize;
	private int totalPages;
	private boolean lastPage;
}
