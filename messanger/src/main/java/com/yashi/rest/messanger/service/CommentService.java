package com.yashi.rest.messanger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.yashi.rest.messanger.database.DatabaseClass;
import com.yashi.rest.messanger.model.Comment;
import com.yashi.rest.messanger.model.ErrorMessage;
import com.yashi.rest.messanger.model.Message;

public class CommentService {
	
	Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		System.out.println("comments = "+messages.get(messageId).getComments());
		return new ArrayList<Comment>(messages.get(messageId).getComments().values());
	}
	
	public Comment addComment(long messageId,Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comments==null){
			comments = new HashMap<Long, Comment>();
		}
		comment.setId(comments.size()+1);
		comment.setCreated(new Date());
		comments.put(comment.getId(), comment);
		Message message = messages.get(messageId);
		message.setComments(comments);
		messages.put(messageId, message);
		System.out.println(messages.get(messageId).getComments().get(comment.getId()));
		return comment;
	}
	
	public Comment getComment(long messageId,long commentId){
		Message message = messages.get(messageId);
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		if(message == null){
			throw new WebApplicationException(response);
		}
		Comment comment = message.getComments().get(commentId); 
		if(comment == null){
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment updateComment(long messageId,Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comment.getId()<=0){
			return null;
		} else{
			comments.put(comment.getId(), comment);
			messages.get(messageId).setComments(comments);
			return comment;
		}
	}
	
	public Comment deleteComment(long messageId, long commentId){
		return messages.get(messageId).getComments().remove(commentId);
	}
}
