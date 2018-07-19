package com.yashi.rest.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yashi.rest.messanger.database.DatabaseClass;
import com.yashi.rest.messanger.model.Profile;

public class ProfileService {
	private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		System.out.println("ProfileService constructor called");
		profiles.put("yashi", new Profile(1, "Yashi", "Sharma"));
		profiles.put("john", new Profile(2, "John", "Smith"));
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profile.setProfileName(profile.getFirstName().toLowerCase());
		profiles.put(profile.getFirstName().toLowerCase(), profile);
		return profile;
	}
	
	public Profile getProfile(String profileName){
		System.out.println("getProfile() = "+profileName);
		System.out.println("getProfile() = "+profiles.get(profileName));
		return profiles.get(profileName);
	}
	
	public List<Profile> getAllProfiles(){
		System.out.println(profiles.values());
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getFirstName().toLowerCase(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		Profile profile = profiles.get(profileName);
		System.out.println("removerProfiles() = "+profiles.remove(profileName));
		System.out.println("removerProfiles() = "+profiles.values());
		return profile;
	}
}