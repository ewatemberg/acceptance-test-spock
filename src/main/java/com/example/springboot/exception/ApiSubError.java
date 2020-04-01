package com.example.springboot.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiSubError {

	private String field;
	private String param;
	@JsonProperty("rejected_value")
	private Object rejectedValue;
    private String message;

	public ApiSubError(String message) {
		this.message = message;
	}

	public ApiSubError(ObjectError oe){
		this.message = oe.getDefaultMessage();
	}

}
