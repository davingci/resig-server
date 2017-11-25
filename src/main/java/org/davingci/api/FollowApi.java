/**
 *@author huangdongxu
 *@Date Nov 22, 2017
 *1. 添加关注
 *2. 取消关注
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
import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Follow;
import org.davingci.pojo.User;
import org.davingci.security.AuthPrincipal;
import org.davingci.util.CalendarUtil;
import org.davingci.util.ResponseUtil;

@Path("/follow")
public class FollowApi {

	@Context
    SecurityContext securityContext;
	
	/**
	 * @api {post} /follow/add Add Follow
	 * @apiVersion 0.1.0
	 * @apiName AddFollow
	 * @apiGroup Follow
	 * @apiPermission authorization
	 * @apiDescription add follow
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} followeeId userId of the user who you would like to follow.
	
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
	@Secured
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response add(@FormParam("followeeId") int followeeId) {
		Date createAt = CalendarUtil.getNow();
		int followerId = ((AuthPrincipal) securityContext.getUserPrincipal()).getUserId();
		Follow follow = new Follow();
		follow.createAt(createAt).followeeId(followeeId).followerId(followerId);
		
		HibernateDao dao = new HibernateDao();
		dao.add(follow);
		
		//关注者的关注者数量＋1
		User follower = (User) dao.findById(User.class, followerId);
		follower.addFollower();
		dao.update(follower);
		
		//被关注者的粉丝数量＋1
		User followee = (User) dao.findById(User.class, followeeId);
		followee.addFollowee();
		dao.update(followee);
		
		ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
		return Response.ok().entity(ru).build();
		
	}
	
	/**
	 * @api {post} /follow/cancle Cancle Follow
	 * @apiVersion 0.1.0
	 * @apiName CancleFollow
	 * @apiGroup Follow
	 * @apiPermission authorization
	 * @apiDescription cancle follow
	 * 
	 * @apiUse Header
	 
	 * @apiParam {String} userId of the user who you would like to cancle follow.
	
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
	@Secured
	@POST
	@Path("/cancle")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response delete(@FormParam("followeeId") int followeeId) {
		
		int followerId = ((AuthPrincipal) securityContext.getUserPrincipal()).getUserId();
		
		HibernateDao dao = new HibernateDao();
		
		//String hql = "FROM User U WHERE U.username = " + "'" + username + "'" + " and U.password = " + "'" + password + "'";
		
		String hql = "FROM Follow F WHERE F.followeeId = " + "'" + followeeId + "'" + "and F.followerId = " + "'" + followerId + "'";
		Follow follow = (Follow) dao.findOneByHQL(hql);
		if(follow != null) {
			dao.delete(follow);
			
			//关注者的关注者数量-1
			User follower = (User) dao.findById(User.class, followerId);
			follower.deleteFollower();
			dao.update(follower);
			
			//被关注者的粉丝数量-1
			User followee = (User) dao.findById(User.class, followeeId);
			followee.deleteFollowee();
			dao.update(followee);
			
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("success.").build();
			return Response.ok().entity(ru).build();
		}
		else {
			ResponseUtil ru = new ResponseUtil.ResponseBuilder().code(200).message("cancel follow failure. followerId or followeeId is incorrect.").build();
			return Response.ok().entity(ru).build();
		}
			
		
	}
	
	
}
