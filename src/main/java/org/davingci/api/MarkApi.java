/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.api;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.davingci.annotation.Secured;
import org.davingci.pojo.Blog;
import org.davingci.pojo.Favourite;
import org.davingci.pojo.Mark;
import org.davingci.security.AuthPrincipal;
import org.davingci.service.BlogService;
import org.davingci.service.FavouriteService;
import org.davingci.service.MarkService;
import org.davingci.util.CalendarUtil;
import org.davingci.util.ResponseUtil;

@Path("/mark")
public class MarkApi {

	@Context
    SecurityContext sc;
	
	MarkService mService = new MarkService();
	BlogService bService = new BlogService();
	
	/**
	 * @api {post} /mark/add Mark
	 * @apiVersion 0.1.0
	 * @apiName Mark
	 * @apiGroup Mark
	 * @apiPermission authorization
	 * @apiDescription mark blog
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} blogId the id of the blog which you would like to mark.
	
	 * @apiUse Success
	 * @apiSuccessExample Success-Response:
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "success.",
	 *       "data":""
	 *     }
	 
	 * @apiUse Unauthorized
	 */
	/**
	 * insert record into favourite table
	 * update markCount in blog table
	 * @return
	 */
	@Secured
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response add(@FormParam("blogId") int blogId) {
		
		Blog blog = bService.get(blogId);
		blog.addMark();
		bService.update(blog);
		
		int markUserId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		Mark mark = new Mark();
		mark.authorId(blog.getUserId()).blogId(blogId).markUserId(markUserId).createAt(CalendarUtil.getNow());
		mService.add(mark);
		
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
		return Response.ok().entity(ru).build();
		
	}
	
	/**
	 * @api {post} /mark/cancle Mark
	 * @apiVersion 0.1.0
	 * @apiName CancleMark
	 * @apiGroup Mark
	 * @apiPermission authorization
	 * @apiDescription cancle mark blog
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} blogId the id of the blog which you would like to cancle mark.
	
	 * @apiUse Success
	 * @apiSuccessExample Success-Response:
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "success.",
	 *       "data":""
	 *     }
	 
	 * @apiUse Unauthorized
	 */
	/**
	 * insert record into favourite table
	 * update markCount in blog table
	 * @return
	 */
	@Secured
	@POST
	@Path("/cancle")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response cancle(@FormParam("blogId") int blogId) {
		
		Blog blog = bService.get(blogId);
		blog.cancleMark();
		bService.update(blog);
		
		int markUserId = ((AuthPrincipal)sc.getUserPrincipal()).getUserId();
		Mark mark = mService.find(markUserId,blogId);
		mService.delete(mark);
		
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
		return Response.ok().entity(ru).build();
	}
}
