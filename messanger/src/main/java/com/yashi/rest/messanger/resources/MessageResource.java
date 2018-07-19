package com.yashi.rest.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.yashi.rest.messanger.model.Message;
import com.yashi.rest.messanger.resources.beans.MessageFilterBean;
import com.yashi.rest.messanger.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	private MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		
		if(filterBean.getYear()>0)
			return messageService.getAllMessagesForYear(filterBean.getYear());
		if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
		Message message = messageService.getMessage(messageId);
		message.addLinks(getUriForSelf(uriInfo, messageId), "self");
		message.addLinks(getUriForProfile(uriInfo, message), "profile");
		message.addLinks(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
		//return Response.created(new URI("messanger/webapi/messages/"+newMessage.getId())).entity(newMessage).build();
		//return Response.status(Status.CREATED).entity(newMessage).build();
		//return messageService.addMessage(message);

	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessage(@PathParam("messageId")long messageId){
		return messageService.removeMessage(messageId);
	}
	
	//this can work but this is not ideal because this class is for messages only!!
	//so tell jersey to not call this function and do some action but rather hand over the request to
	//some other resource
	/*@GET
	@Path("/{messageId}/comments")
	public String getComments(){
		return "test";
	}*/
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	public String getUriForSelf(UriInfo uriInfo, Long messageId){
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(messageId)).build().toString();
	}
	public String getUriForProfile(UriInfo uriInfo, Message message){
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString().toLowerCase();
	}
	private String getUriForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()).build().toString();
	}
}