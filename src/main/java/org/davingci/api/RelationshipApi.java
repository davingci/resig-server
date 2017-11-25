/**
 *@author huangdongxu
 *@Date Nov 22, 2017
 *
 *1. 查询用户和用户之间关注与否
 *2. 查询用户和博客之间：是否收藏，是否喜欢
*/

package org.davingci.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.davingci.annotation.Secured;
import org.davingci.pojo.status.FavouriteMarkStatus;
import org.davingci.security.AuthPrincipal;
import org.davingci.service.RelationshipService;
import org.davingci.util.ResponseUtil;

@Path("/relationship")
public class RelationshipApi {
	
	@Context
    SecurityContext securityContext;

	RelationshipService service = new RelationshipService();
	
	/**
	 * @api {post} /relationship/user User Relationship
	 * @apiVersion 0.1.0
	 * @apiName User User Relationship
	 * @apiGroup Relationship
	 * @apiPermission authorization
	 * @apiDescription check whether you follow somebody.
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} userId the userId of the user who you would like to know whether you have followed.
	
	 * @apiUse Success
	 * @apiSuccessExample Followed:
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "isFollowed",
	 *       "data":true
	 *     }
	 * @apiSuccessExample unFollowed:
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "unFollowed",
	 *       "data":false
	 *     }	 
	 * @apiUse Unauthorized
	 */
	@Secured
	@POST
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public Response user2user(@FormParam("userId") int userId){
		
		int selfId = ((AuthPrincipal) securityContext.getUserPrincipal()).getUserId();
		
		if(service.isFollowed(selfId, userId)) {
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("isFollowed").data(true).build();
			return Response.ok().entity(ru).build();
		}
		else {
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("unFollowed").data(false).build();
			return Response.ok().entity(ru).build();
		}
	}
	
	/**
	 * @api {post} /relationship/blog User Blog Relationship
	 * @apiVersion 0.1.0
	 * @apiName User Blog Relationship
	 * @apiGroup Relationship
	 * @apiPermission authorization
	 * @apiDescription check whether you mark,favourite the blog.
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} blogId the blogId of the blog you would like to know whether you have mark or add to you favourite list.
	
	 * @apiUse Success
	 * @apiSuccessExample Favourite Marked
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "",
	 *       "data":"FavouriteMarked"
	 *     }
	 * @apiSuccessExample Favourite UnMarked
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "",
	 *       "data":"FavouriteUnMarked"
	 *     }
	 * @apiSuccessExample UnFavourite Marked
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "",
	 *       "data":"UnFavouriteMarked"
	 *     }
	 * @apiSuccessExample UnFavourite UnMarked
	 *     HTTP/1.1 200 OK
	 *     {
	 *       "code": 200,
	 *       "message": "",
	 *       "data":"UnFavouriteUnMarked"
	 *     }	 
	 * @apiUse Unauthorized
	 */
	@Secured
	@POST
	@Path("/blog")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public Response user2blog(@FormParam("blogId") int blogId){
		int selfId = ((AuthPrincipal) securityContext.getUserPrincipal()).getUserId();
		boolean isFavourite = service.isFavourited(selfId, blogId);
		boolean isMarked = service .isMarked(selfId, blogId);
		ResponseUtil ru = new ResponseUtil();
		if( isFavourite && isMarked) {
			ru.data(FavouriteMarkStatus.FavoritedMarked);
		}
		else if(isFavourite && !isMarked) {
			ru.data(FavouriteMarkStatus.FavouritedUnMark);
		}
		else if(!isFavourite && isMarked) {
			ru.data(FavouriteMarkStatus.UnFavouriteMarked);
		}
		else if(!isFavourite && !isMarked) {
			ru.data(FavouriteMarkStatus.UnFavouriteUnMark);
		}
		return Response.ok().entity(ru).build();
	}
	
	
}
