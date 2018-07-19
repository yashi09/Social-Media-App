package com.yashi.rest.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yashi.rest.messanger.database.DatabaseClass;
import com.yashi.rest.messanger.exception.DataNotFoundException;
import com.yashi.rest.messanger.model.Message;
//this class will actually call dao and will do operations with help of db
public class MessageService {
	//this static map is there because this map will be there as long as we dont restart the server.
	//we can add a message n one request, update it in another and so on
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		System.out.println("MessageService contructor called");
		messages.put(1L, new Message(1, "Hello World!", "Yashi"));
		messages.put(2L, new Message(2, "Hello Jersey!", "Yashi"));
	}
	
	public List<Message> getAllMessages(){
		System.out.println(messages.values());
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar calendar = Calendar.getInstance();
		for(Message message : messages.values()){
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		List<Message> messagesPaginated = new ArrayList<Message>(messages.values());
		if(start+size>messagesPaginated.size()){
			return new ArrayList<Message>();
		}
		return messagesPaginated.subList(start, start+size);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id "+id+" not found!");
		}
		return messages.get(id);
	}
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	public Message removeMessage(long id){
		System.out.println("removeMessage = "+messages.values());
		return messages.remove(id);
	}
}
