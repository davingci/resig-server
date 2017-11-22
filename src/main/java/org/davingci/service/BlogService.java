/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.service;

import org.davingci.dao.HibernateDao;
import org.davingci.pojo.Blog;

public class BlogService {
	
	public Blog get(int blogId) {
		HibernateDao dao = new HibernateDao();
		Blog blog = (Blog) dao.findById(Blog.class, blogId);
		return blog;
	}
	
	public void update(Blog blog) {
		HibernateDao dao = new HibernateDao();
		dao.update(blog);
	}

}
