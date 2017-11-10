/**
 *@author huangdongxu
 *@Date Nov 10, 2017
*/

package org.davingci.api;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.davingci.dao.HibernateDao;
import org.davingci.pojo.User;
import org.davingci.util.ResponseUtil;

import com.alibaba.fastjson.JSON;


@Path("/user")
public class UserService {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {
		String hql = "FROM User U WHERE U.username = " + "'" + username + "'" + " and U.password = " + "'" + password + "'";
		HibernateDao dao = new HibernateDao();
		List list = dao.findByHQL(hql);
		
		ResponseUtil ru = new ResponseUtil();
		ru.code(201).message("username or password is incorrect.");
		if(list.size() > 0) {
			User user = (User) list.get(0);
			user.setPassword(null);
			ru.code(200).message("correct").data(JSON.toJSON(user));
		}
		return Response.status(200).entity(ru).build();
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
