/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.api;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.davingci.annotation.Secured;
import org.davingci.dao.HibernateDao;
import org.davingci.exception.DavingciException;
import org.davingci.pojo.Blog;
import org.davingci.pojo.User;
import org.davingci.security.AuthPrincipal;
import org.davingci.service.UserService;
import org.davingci.util.DigestUtil;
import org.davingci.util.ResigKeyGenerator;
import org.davingci.util.ResponseUtil;
import org.davingci.util.TokenUtil;

@Path("/user")
public class UserApi {
	
	@Context
    SecurityContext sc;
	
	UserService service = new UserService();
	
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
		} catch(DavingciException e ) {	
			ResponseUtil ru = new ResponseUtil();
			
			ru.message(e.getMessage()).code(401);
			return Response.status(Response.Status.OK).entity(ru).build();
		} catch(NullPointerException e) {
			ResponseUtil ru = new ResponseUtil();
			ru.message(e.getMessage());
			return Response.status(Response.Status.OK).entity(ru).build();
		}
        
	}
	
	private User authenticate(String username, String password)  {
		String hql = "FROM User U WHERE U.username = " + "'" + username + "'";
		HibernateDao dao = new HibernateDao();
		List list = dao.findByHQL(hql);
			
		if(list.size() > 0) {
			User user = (User) list.get(0);
			password = DigestUtil.sha256Hex(password);
			
			if(user.getPassword().equals(password)) {
				user.setPassword(null);
				return user;
			}
			else {
				
				System.out.println("login password:" + password);
				System.out.println("db password:" + user.getPassword());
				throw new DavingciException("password is wrong.");
			}
		}
		else {
			
			throw new DavingciException("username does not exist.");
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
			password = DigestUtil.sha256Hex(password);
			User user = new User(username,password);
			
			dao.add(user);
			ru.code(200).message("register success.");
			return Response.status(200).entity(ru).build();
		}
		
	}
	
	@Secured
	@GET
	@Path("/get/{userId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response get(@PathParam("userId") int userId) {
		HibernateDao dao = new HibernateDao();
		User user = (User) dao.findById(User.class, userId);
		if(user != null) {
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).data(user).message("success.").build();
			return Response.ok().entity(ru).build();
		}
		else {
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(201).data(user).message("can not find user.").build();
			return Response.ok().entity(ru).build();
		}
	}
	
	

	@Secured
	@GET
	@Path("/list/follower")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response followers() {
		int userId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		List<User> users = service.getFollowers(userId);
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).data(users).build();
		return Response.ok().entity(ru).build();
	}
	
	@Secured
	@GET
	@Path("/list/followee")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response followees() {
		int userId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		List<User> users = service.getFollowees(userId);
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).data(users).build();
		return Response.ok().entity(ru).build();
	}
	
	@Secured
	@GET
	@Path("/list/favourite")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response favourites() {
		int userId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		List<Blog> blogs = service.getFavourites(userId);
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).data(blogs).build();
		return Response.ok().entity(ru).build();
	}
	
	@Secured
	@GET
	@Path("/list/mark")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response marks() {
		int userId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		List<Blog> blogs = service.getMarks(userId);
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).data(blogs).build();
		return Response.ok().entity(ru).build();
	}
}
