/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.service;

import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Mark;

public class MarkService {
	
	HibernateDao dao = new HibernateDao();
	
	public void add(Mark mark) {
		dao.add(mark);
	}
	
	public void delete(Mark mark) {
		dao.delete(mark);
	}
	
	/**
	 * 
	 * @param mId markUserId
	 * @param bId blogId
	 * @return
	 */
	public Mark find(int mId, int bId) {
		String hql = "From Mark M where markUserId= " + "'" + mId + "'" + " and blogId= " + "'" + bId + "'";
		return (Mark) dao.findOneByHQL(hql);
	}

}
