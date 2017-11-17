/**
 *@author huangdongxu
 *@Date Nov 18, 2017
*/

package org.davingci.security;

import java.security.Principal;

import javax.ws.rs.NotAuthorizedException;

import org.davingci.pojo.User;

public class AuthPrincipal implements Principal {
	
	private User user;
	
	public AuthPrincipal(String username, int userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUsername(username);
		this.user = user;
	}

	@Override
	public String getName() {
		
		return null;
	}
	
	public String getUsername() {
		checkState();
		return user.getUsername();
	}
	
	public int getUserId() {
		checkState();
		return user.getUserId();
	}
	
	public void checkState() {
		if(user == null) {
			throw new NotAuthorizedException("Principal is invalid.");
		}
	}
	
	

}
