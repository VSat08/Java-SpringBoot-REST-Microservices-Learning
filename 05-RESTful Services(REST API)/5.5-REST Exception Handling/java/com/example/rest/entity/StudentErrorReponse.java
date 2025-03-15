package com.example.rest.entity;

public class StudentErrorReponse {

	private int status;
	private String messsage;
	private long timeStamp;

	public StudentErrorReponse() {
	}

	public StudentErrorReponse(int status, String messsage, long timeStamp) {
		this.status = status;
		this.messsage = messsage;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
