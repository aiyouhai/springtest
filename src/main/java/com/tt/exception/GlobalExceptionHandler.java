package com.tt.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.tt.ui.vo.ResponseObject;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ResponseObject jsonErrorHandler(HttpServletRequest req, MyException e) {
		ResponseObject responseObject = new ResponseObject(e.getCode(), e.getMessage(), req.getRequestURL().toString());

		return responseObject;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public ResponseObject handlerMaxUploadSizeExceededException(HttpServletRequest req,
			MaxUploadSizeExceededException e) {
		return new ResponseObject(205, e.getMessage());

	}

	@ExceptionHandler
	@ResponseBody
	public ResponseObject ErrorHandler(AuthorizationException e) {
		return new ResponseObject(205, e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)

	@ResponseBody
	public ResponseObject ExceptionHandler(HttpServletRequest req, Exception e) {
		ResponseObject responseObject = new ResponseObject(e.getMessage());
		return responseObject;
	}

}
