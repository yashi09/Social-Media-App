package com.yashi.rest.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.yashi.rest.messanger.model.ErrorMessage;
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException arg0) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(arg0.getMessage(), 404, "http://google.co.in");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		//return Response.status(Status.NOT_FOUND).build();
	}
	
}
