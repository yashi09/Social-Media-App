package com.yashi.rest.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	@GET
	@Path("annotations")
	//we use HeaderParam to send meta data like authentication token etc..
	//example:- you can create your own authSessionId as headerParam and send in some token value 
	public String getParamUsingAnnotations(@MatrixParam("param")String matrixParam, 
											@HeaderParam("customHeader")String header,
											@CookieParam("name")String cookie){
		return "MatrixParam : "+matrixParam+" HeaderParam: "+header+" cookie: "+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders){
		String mts = httpHeaders.getAcceptableMediaTypes().toString();
		String path = uriInfo.getAbsolutePath().toString();
		return "path : "+path+ " mts: "+mts;
	}
}
