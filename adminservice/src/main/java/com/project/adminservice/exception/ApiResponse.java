package com.project.adminservice.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ApiResponse 
{

    private Date timestamp;
	private String message;
	private boolean success;
    
    public ApiResponse(Date timestamp, String message, boolean success) 
    {
        this.timestamp = timestamp;
        this.message = message;
    }
    
}
