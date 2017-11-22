/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.davingci.pojo.Blog;
import org.davingci.pojo.View;
import org.davingci.security.AuthPrincipal;
import org.davingci.util.CalendarUtil;
import org.davingci.util.ResponseUtil;

@Path("/blog")
public class BlogApi {

	@Context
    SecurityContext securityContext;	

	@Secured
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response add(@FormParam("title") String title, 
						@FormParam("html") String html,
						@FormParam("markdown") String markdown,
						@FormParam("blogEditor") String blogEditor) {
		
		Date createAt = CalendarUtil.getNow();
		Date lastModifyAt = CalendarUtil.getNow();
		
		AuthPrincipal principal = (AuthPrincipal) securityContext.getUserPrincipal();

	    String username = principal.getUsername();
	    int userId = principal.getUserId();
	    
	    //TODO
	    //Extra content from html
	    //index with ElasticSearch
		
		Blog blog = new Blog.BlogBuilder()
							.title(title)
							.markdown(markdown)
							.html(html)
							.blogEditor(blogEditor)
							.userId(userId)
							.username(username)
							.createAt(createAt)
							.lastModifyAt(lastModifyAt)
							.build();
		HibernateDao dao = new HibernateDao();
		dao.add(blog);
		
		
		
		ResponseUtil ru = new ResponseUtil();
		ru.code(200).message("publish blog success.");
		return Response.status(200).entity(ru).build();
	}
	
	@Secured
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response add(@FormParam("title") String title, 
						@FormParam("html") String html,
						@FormParam("markdown") String markdown,
						@FormParam("blogId") int blogId) {
		
		Date lastModifyAt = CalendarUtil.getNow();
		
		HibernateDao dao = new HibernateDao();
		Blog blog = (Blog) dao.findById(Blog.class, blogId);
		
		ResponseUtil ru = new ResponseUtil();
		
		if(blog != null) {
			blog.setTitle(title);
			blog.setHtml(html);
			blog.setMarkdown(markdown);
			blog.setLastModifyAt(lastModifyAt);
			dao.update(blog);		
			ru.code(200).message("update blog success.");
			
		}
		else {
			ru.code(201).message("blogId is incorrect.");
			
		}
		return Response.status(200).entity(ru).build();
	}
	
	/**
	 * get the blog
	 * insert view into view table
	 * update blog viewCount in blog table
	 * return blog
	 * @param blogId
	 * @return
	 */
	@GET
	@Path("/get/{blogId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response get(@PathParam("blogId") int blogId) {

		ResponseUtil ru = new ResponseUtil();
		HibernateDao dao = new HibernateDao();
		Blog blog = (Blog) dao.findById(Blog.class, blogId);
		if(blog != null) {
			
			//get blog, add view
			int authorId = blog.getUserId();
			

		   
		    int viewUserId = -1;
			Date createAt = CalendarUtil.getNow();
			View view = new View();
			view.authorId(authorId).blogId(blogId).viewUserId(viewUserId).createAt(createAt);
			dao.add(view);
			
			//update blog views
			blog.setViewCount(blog.getViewCount()+1);
			dao.update(blog);
			
			ru.code(200).message("get blog success.").data(blog);
			
		}
		else {
			ru.code(201).message("blogId is incorrect or blog has been deleted");
			
		}
		return Response.status(200).entity(ru).build();
	}
	
	@Secured
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response delete(@FormParam("blogId") int blogId) {
		ResponseUtil ru = new ResponseUtil();
		HibernateDao dao = new HibernateDao();
		Blog blog = (Blog) dao.findById(Blog.class, blogId);
		if(blog != null) {
			dao.delete(blog);
			ru.code(200).message("delete blog success.");
			
		}
		else {
			ru.code(201).message("blogId is incorrect.");
			
		}
		return Response.status(200).entity(ru).build();
		
	}
	
	@POST
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response query(@FormParam("startTime") String startTime, @FormParam("endTime") String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		ArrayList<Date> list = new ArrayList<Date>();
		list.add(startDate);
		list.add(endDate);
		
		HibernateDao dao = new HibernateDao();
		String hql = "FROM Blog B WHERE B.createAt between ? and ?";
		List<?> blogs = dao.findByHQL(hql,list);
		
		
		
		ResponseUtil ru = new ResponseUtil();
		ru.code(200).message("success.").data(blogs);
		return Response.status(200).entity(ru).build();
	}
}
