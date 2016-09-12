package com.singplayground.showcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.singplayground.showcase.dto.ErrorMsgDto;
import com.singplayground.showcase.exception.ControllerGenericException;
import com.singplayground.showcase.exception.IllegalArgumentGenericException;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(ControllerGenericException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleControllerException(ControllerGenericException ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode("001");
		errorMsgDto.setErrorMsg("Exception");
		return errorMsgDto;
	}

	@ExceptionHandler(IllegalArgumentGenericException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleIllegalArgumentException(IllegalArgumentGenericException ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode("002");
		errorMsgDto.setErrorMsg("Illegal Argument");
		return errorMsgDto;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorMsgDto handleAllException(Exception ex) {
		ErrorMsgDto errorMsgDto = new ErrorMsgDto();
		errorMsgDto.setErrorCode("003");
		errorMsgDto.setErrorMsg("exception");
		return errorMsgDto;
	}
}