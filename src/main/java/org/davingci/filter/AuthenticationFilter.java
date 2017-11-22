/**
 *@author huangdongxu
 *@Date Nov 17, 2017
*/

package org.davingci.filter;

import java.io.IOException;
import java.security.Key;
import java.security.Principal;

import javax.annotation.Priority;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.davingci.annotation.Secured;
import org.davingci.dao.HibernateDao;
import org.davingci.pojo.User;
import org.davingci.security.AuthenticationSecurityContext;
import org.davingci.util.ResigKeyGenerator;
import org.davingci.util.TokenUtil;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
        String authorizationHeader =
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        Key key = ResigKeyGenerator.generateKey();
        if (TokenUtil.isValid(token, key)) {
            String username = TokenUtil.getUserName(token, key);
            int userId = TokenUtil.getUserId(token, key);
            
            if (username != null && userId != -1) {
                User user = null;
                try {
                	HibernateDao dao = new HibernateDao();
                    user = (User) dao.findById(User.class, userId);
                } catch (EntityNotFoundException e) {
                    //logger.info("User not found " + name);
                }
                if (user != null) {
                    if (user.getUsername().equals(username)) {
                    	
                    	requestContext.setSecurityContext(new AuthenticationSecurityContext(username,userId));
                        
                    	return;
                    } else {
                        //logger.info("Version or roles did not match the token");
                    }
                } else {
                    //logger.info("User not found");
                }
            } else {
                //logger.info("name, roles or version missing from token");
            }
        } else {
            //logger.info("token is invalid");
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }

        
        	
           
        
    }


		
	

}
