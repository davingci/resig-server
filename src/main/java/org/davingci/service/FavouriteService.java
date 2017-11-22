/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.service;

import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Favourite;

public class FavouriteService {

	public void add(Favourite f) {
		HibernateDao dao = new HibernateDao();
		dao.add(f);
	}
	
	public void cancle(Favourite f) {
		HibernateDao dao = new HibernateDao();
		dao.delete(f);
	}
	
	/**
	 * 
	 * @param fId favouriteUserId
	 * @param bId blogId
	 */
	public Favourite find(int fId, int bId) {
		HibernateDao dao = new HibernateDao();
		String hql = "From Favourite F where F.favouriteUserId= " + "'" + fId + "'" + " and blogId=" + "'" + bId + "'";
		return (Favourite) dao.findOneByHQL(hql);
	}
}
