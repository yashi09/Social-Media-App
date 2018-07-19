package com.yashi.rest.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.yashi.rest.messanger.model.Comment;
import com.yashi.rest.messanger.model.Message;
import com.yashi.rest.messanger.model.Profile;
import com.yashi.rest.messanger.model.Comment;
//acting as a stub class for database or dao
//this is not thread safe. there is no concurrency protection here. 
//it is assumed here that only one person is learning this
//this is only for learning purposes. please dont write code like this in production!
public class DatabaseClass {
	private static Map<Long,Message> messages = new HashMap<Long, Message>();
	private static Map<String,Profile> profiles = new HashMap<String, Profile>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	public static Map<String,Profile> getProfiles(){
		return profiles;
	}
}
