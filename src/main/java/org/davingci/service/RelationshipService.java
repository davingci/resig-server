/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.service;

import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Favourite;
import org.davingci.pojo.Follow;
import org.davingci.pojo.Mark;

public class RelationshipService {
	
	public boolean isFollowed(int selfId, int otherId) {
		boolean isFollowed = false;
		HibernateDao dao = new HibernateDao();
		String hql = "From Follow F where F.followeeId = " + "'" + otherId + "'" + "and F.followerId = " + "'" + selfId + "'";
		Follow follow = (Follow) dao.findOneByHQL(hql);
		isFollowed = follow!=null ? true : false;
		return isFollowed;
	}
	
	public boolean isFavourited(int selfId, int blogId) {
		boolean isFavourited = false;
		HibernateDao dao = new HibernateDao();
		String hql = "From Favourite F where F.favouriteUserId=" + "'" + selfId + "'" + "and blogId=" + "'" + blogId + "'";
		Favourite f = (Favourite) dao.findOneByHQL(hql);
		isFavourited = f != null ? true : false;
		return isFavourited;
	}
	
	public boolean isMarked(int selfId, int blogId) {
		boolean isMarked = false;
		HibernateDao dao = new HibernateDao();
		String hql = "From Mark M where M.markUserId=" + "'" + selfId + "'" + "and blogId=" + "'" + blogId + "'";
		
		Mark m = (Mark) dao.findOneByHQL(hql);
		isMarked = m != null ? true : false;
		return isMarked;
	}

}
