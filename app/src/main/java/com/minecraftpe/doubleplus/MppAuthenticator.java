package com.minecraftpe.doubleplus;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MppAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MppAuthenticator() {
	}

	public MppAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
