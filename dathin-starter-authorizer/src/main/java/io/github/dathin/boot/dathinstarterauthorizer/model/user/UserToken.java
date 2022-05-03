package io.github.dathin.boot.dathinstarterauthorizer.model.user;

import java.util.Date;


public class UserToken {

	private int id;

	private Date expiresAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
}
