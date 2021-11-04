package com.ace.springjdbcdemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonErrorResponse {

	private String ErrorMassage;
}
