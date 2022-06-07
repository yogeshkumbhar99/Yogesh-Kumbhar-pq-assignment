package com.payconiq.exception;

public class ErrorDetails{
	
    private String errorMessage;
    private String details;

    public ErrorDetails(String errorMessage, String details) {
         super();
         this.errorMessage = errorMessage;
         this.details = details;
    }
    public ErrorDetails(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
   }

    public String getMessage() {
         return errorMessage;
    }

    public String getDetails() {
         return details;
    }
}