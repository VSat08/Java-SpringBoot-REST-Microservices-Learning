package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rest.entity.StudentErrorReponse;

import exception.StudentNotFoundException;

@ControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StudentErrorReponse> handleException(StudentNotFoundException e) {

		StudentErrorReponse error = new StudentErrorReponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMesssage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<StudentErrorReponse>(error, HttpStatus.NOT_FOUND);

	}

}
