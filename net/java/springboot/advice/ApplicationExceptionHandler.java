package net.java.springboot.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.java.springboot.Exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	   @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exp)
	    {
	        Map<String,String> errorMap = new HashMap<>();
	        exp.getBindingResult().getFieldErrors().forEach(error ->
	        {
	            errorMap.put(error.getField(),error.getDefaultMessage());
	        });
	        return errorMap;
	    }

	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler(UserNotFoundException.class)
	    public Map<String,String> handleBusinessException(UserNotFoundException ex)
	    {
	        Map<String,String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage",ex.getMessage());
	        return errorMap;
	    }
}
