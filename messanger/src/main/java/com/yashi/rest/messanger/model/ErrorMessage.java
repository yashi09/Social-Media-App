package com.yashi.rest.messanger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String documentation;
	
	public ErrorMessage(){
	}
	public ErrorMessage(String errorMessage, int errorCode, String documentation){
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage(){
		return this.errorMessage;
	}
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
	public int getErrorCode(){
		return this.errorCode;
	}
	public void setDocumentation(String documentation){
		this.documentation = documentation;
	}
	public String getDocumentation(){
		return this.documentation;
	}
}