package com.henriquevenancio.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.henriquevenancio.cursomc.services.exceptions.DataIntegrityException;
import com.henriquevenancio.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	
	
	//A Validação foi posta no próprio Resource.
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	//@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ExceptionHandler(Exception.class)	
//	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
//		
//		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
//		
//		for (FieldError x : e.getBindingResult().getFieldErrors()){
//			err.addError(x.getField(), x.getDefaultMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
//	}

}
