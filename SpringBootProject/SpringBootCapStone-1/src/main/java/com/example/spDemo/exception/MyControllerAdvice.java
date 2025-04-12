package com.example.spDemo.exception;

//import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(ResourceNotFound.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponseFormat handleCustomerNotFoundEx(ResourceNotFound ex,HttpServletRequest req) {
		ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(ex.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
	}
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ExceptionResponseFormat handleException(Exception ex,HttpServletRequest req) {
    	ExceptionResponseFormat erf=new ExceptionResponseFormat();
    	//System.out.println(RequestMethod.valueOf("POST"));
    	System.out.println(ex.getMessage());
    	System.out.println(ex.getClass().getSimpleName());
    	if(ex.getMessage().equals("No value present")) {
    	         erf.setError("No Such Details are Found");
    	}
    	else {
    		erf.setError(ex.getMessage());
    	}
		erf.setUrl(req.getRequestURI());
		return erf;
    }
    @ExceptionHandler(FromAccountDoesnotExist.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponseFormat handleFromAccountDoesnotExist(FromAccountDoesnotExist ex,HttpServletRequest req) {
		ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(ex.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
	}
    @ExceptionHandler(ToAccountDoesnotExist.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponseFormat handleToAccountDoesnotExist(ToAccountDoesnotExist ex,HttpServletRequest req) {
		ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(ex.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
	}
    @ExceptionHandler(AmountInsufficientException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponseFormat handleAmountInsufficientException(AmountInsufficientException ex,HttpServletRequest req) {
		ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(ex.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
	}
    @ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	ExceptionResponseFormat handleAccountNotFoundException(AccountNotFoundException ex,HttpServletRequest req) {
		ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(ex.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
	}
    @ExceptionHandler(ProperDataNotAddedException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionResponseFormat handleProperDataNotFoundException(ProperDataNotAddedException exception,HttpServletRequest req) {
    	ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(exception.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
    }
    @ExceptionHandler(AccountDoesNotExist.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionResponseFormat handleAccountDoesNotExistException(AccountDoesNotExist exception,HttpServletRequest req) {
    	ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(exception.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
    }
    @ExceptionHandler(CustomerDoesNotExist.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionResponseFormat handleCustomerDoesNotExistException(CustomerDoesNotExist exception,HttpServletRequest req) {
    	ExceptionResponseFormat erf=new ExceptionResponseFormat();
		erf.setError(exception.getMessage());
		erf.setUrl(req.getRequestURI());
		return erf;
    }
}
