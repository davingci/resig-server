/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.service;

import java.util.List;

import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Blog;
import org.davingci.pojo.User;

public class UserService {
	
	HibernateDao dao = new HibernateDao();
	
	/**
	 * 获取用户的关注者
	 * @param userId
	 * @return
	 */
	public List<User> getFollowers(int userId) {
		return null;
		
	}
	
	/**
	 * 获取用户的粉丝
	 * @param userId
	 * @return
	 */
	public List<User> getFollowees(int userId) {
		return null;	
	}
	
	/**
	 * 获取用户喜爱的文章
	 * @param userId
	 * @return
	 */
	public List<Blog> getFavourites(int userId) {
		return null;
	}
	
	/**
	 * 获取用户收藏的文章
	 * @param userId
	 * @return
	 */
	public List<Blog> getMarks(int userId) {
		return null;
	}

}
