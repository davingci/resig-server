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
import org.davingci.security.AuthPrincipal;
import org.davingci.service.BlogService;
import org.davingci.service.FavouriteService;
import org.davingci.util.CalendarUtil;
import org.davingci.util.ResponseUtil;

@Path("/favourite")
public class FavouriteApi {

	
	@Context
    SecurityContext sc;
	
	/**
	 * @api {post} /favourite/add Add Favourite
	 * @apiVersion 0.1.0
	 * @apiName AddFavourite
	 * @apiGroup Favourite
	 * @apiPermission authorization
	 * @apiDescription add favourite
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} blogId the id of the blog which you would like to add to favourite list.
	
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
	 * update favourteCount in blog table
	 * @return
	 */
	@Secured
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response add(@FormParam("blogId") int blogId) {
		
		//update favouriteCount for blog
		BlogService blogService = new BlogService();
		Blog blog = blogService.get(blogId);
		blog.addFavourite();
		blogService.update(blog);
		
		//add favourite record in favourite table
		int favouriteUserId = ((AuthPrincipal) sc.getUserPrincipal()).getUserId();
		Date createAt = CalendarUtil.getNow();
		Favourite f = new Favourite();
		f.authorId(blog.getUserId()).blogId(blogId).favouriteUserId(favouriteUserId).createAt(createAt);
		FavouriteService fService = new FavouriteService();
		fService.add(f);
		
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
		return Response.ok().entity(ru).build();
		
	}
	
	/**
	 * @api {post} /favourite/cancle Cancle Favourite
	 * @apiVersion 0.1.0
	 * @apiName CancleFavourite
	 * @apiGroup Favourite
	 * @apiPermission authorization
	 * @apiDescription cancle favourite
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} blogId the id of the blog which you would like to  remove from  favourite list.
	
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
	 * delete record from favourite table
	 * update favourite in blog table
	 * @return
	 */
	@Secured
	@POST
	@Path("/cancle")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response cancle(@FormParam("blogId") int blogId) {
		//update favouriteCount for blog
		BlogService blogService = new BlogService();
		Blog blog = blogService.get(blogId);
		blog.cancleFavourite();
		blogService.update(blog);
		
		//cancle favourite
		int favouriteUserId = ((AuthPrincipal) sc.getUserPrincipal()).getUserId();
		FavouriteService fService = new FavouriteService();
		Favourite f = fService.find(favouriteUserId,blogId);
		fService.cancle(f);
		
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
		return Response.ok().entity(ru).build();
		
		
	}
}
