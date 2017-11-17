/**
 *@author huangdongxu
 *@Date Nov 18, 2017
*/

package org.davingci.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import org.davingci.pojo.User;

public class AuthenticationSecurityContext implements SecurityContext {
	
	private Principal principal;

	
	public AuthenticationSecurityContext(String username, int userId) {
		AuthPrincipal principal = new AuthPrincipal(username,userId);
		this.principal = principal;
 
	}

	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return this.principal;
	}

	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

