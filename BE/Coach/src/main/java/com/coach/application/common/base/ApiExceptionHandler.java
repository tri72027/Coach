package com.coach.application.common.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	/*
	 * @ExceptionHandler(Exception.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * public ErrorMessage handleAllException(Exception ex, WebRequest request) {
	 * 
	 * return new ErrorMessage(10000, ex.getLocalizedMessage()); }
	 */
	
	@ExceptionHandler(Exception.class)
	//@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)

	public ResponseEntity<?> INTERNAL_SERVER_ERROR(Exception ex, WebRequest request) {
		ErrorMessage error = new ErrorMessage();
		error.setStatusCode(10010);
		error.setMessage(ex.getLocalizedMessage());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage TodoException(Exception ex, WebRequest request) {
		return new ErrorMessage(10100, "Đối tượng không tồn tại");
	}

}

