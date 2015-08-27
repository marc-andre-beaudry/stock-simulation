package com.maillets.stocksimulation.dto;

import com.maillets.stocksimulation.entities.User;

public class UserDto {

	private String firstName;
	private String lastName;
	private String joinDate;
	private String lastConnect;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastConnect() {
		return lastConnect;
	}

	public void setLastConnect(String lastConnect) {
		this.lastConnect = lastConnect;
	}

	public static UserDto fromUser(User user) {
		UserDto dto = new UserDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setJoinDate(user.getJoinDate().toString());
		dto.setLastConnect(user.getLastConnect().toString());
		return dto;
	}
}
