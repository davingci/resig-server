/**
 *@author huangdongxu
 *@Date Nov 10, 2017
*/

package org.davingci.api;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.davingci.dao.HibernateDao;

import org.davingci.exception.DavingciException;
import org.davingci.pojo.User;
import org.davingci.util.ResigKeyGenerator;
import org.davingci.util.ResponseUtil;
import org.davingci.util.TokenUtil;

import com.alibaba.fastjson.JSON;

import javax.ws.rs.NotAuthorizedException;


@Path("/user")
public class UserService {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {	
		
		try {
			Date expiry = getExpiryDate(60);
	        User user = authenticate(username, password);
	        // Issue a token (can be a random String persisted to a database or a JWT token)
	        // The issued token must be associated to a user
	        // Return the issued token
	        Key key = ResigKeyGenerator.generateKey();
	        String token = TokenUtil.getJWTString(username, user.getUserId(), expiry, key);
	        ResponseUtil ru = new ResponseUtil();
	        ru.data(token);
			return Response.ok(ru).build();
		} catch(NotAuthorizedException e ) {	
			return e.getResponse();
		} catch(NullPointerException e) {
			ResponseUtil ru = new ResponseUtil();
			ru.message(e.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(ru).build();
		}
        
	}
	
	private User authenticate(String username, String password)  {
		String hql = "FROM User U WHERE U.username = " + "'" + username + "'";
		HibernateDao dao = new HibernateDao();
		List list = dao.findByHQL(hql);
			
		if(list.size() > 0) {
			User user = (User) list.get(0);
			if(user.getPassword().equals(password)) {
				user.setPassword(null);
				return user;
			}
			else {
				ResponseUtil ru = new ResponseUtil();
				ru.message("Invalid Password.");
				Response response =  Response.status(Response.Status.UNAUTHORIZED).entity(ru).build();
				throw new NotAuthorizedException("Invalid Password.",response);
			}
		}
		else {
			ResponseUtil ru = new ResponseUtil();
			ru.message("Invalid Username.");
			Response response =  Response.status(Response.Status.UNAUTHORIZED).entity(ru).build();
			throw new NotAuthorizedException("Invalid Username.",response);
		}
	}
	
	 /**
     * get Expire date in minutes.
     *
     * @param minutes the minutes in the future.
     * @return
     */
    private Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response register(@FormParam("username") String username, @FormParam("password") String password) {
		String hql = "FROM User U WHERE U.username = " + "'" + username + "'";
		HibernateDao dao = new HibernateDao();
		List list = dao.findByHQL(hql);
		
		ResponseUtil ru = new ResponseUtil();
		
		if(list.size() > 0) {
			ru.code(201).message("register failure, username is exist.");
			return Response.status(200).entity(ru).build();
		}
		else {
			User user = new User(username,password);
			dao.add(user);
			List l = dao.findByHQL(hql);
			User u = (User) l.get(0);
			u.setPassword(null);
			ru.code(200).message("register success.").data(JSON.toJSON(u));
			return Response.status(200).entity(ru).build();
		}
		
	}
	
	
}
