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
