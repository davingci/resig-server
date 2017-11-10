package org.davingci.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;



public class HibernateDao implements IDao {
	
	 /**
     * 添加实体
     * @param obj，要添加的实体对象
     * @throws Exception
     */
    public void add(Object obj){
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.save(obj);
            session.beginTransaction().commit();
            if(session!=null){
                  session.close();
            }
        } catch (RuntimeException e) {
            session.beginTransaction().rollback();
            if(session!=null){
                  session.close();
            }
            e.printStackTrace();
        }
    }
    
    /**
     * 删除实体
     * @param obj，要删除的实体
     * @throws Exception
     */
    public void delete(Object obj){
        Session session = null;
        try {
            //取得session对象
        	session = HibernateUtils.getSessionFactory().openSession();
            //删除实体
            session.delete(obj);
            //提交事务
            session.beginTransaction().commit();
            if(session!=null){
                  session.close();
            }
        } catch (Exception e) {
            session.beginTransaction().rollback();//事务回滚
            if(session!=null){
                  session.close();
            }
            e.printStackTrace();
        }
    }

    /**
     * 更新实体
     * @param obj，要更新的实体
     * @throws Exception
     */
    public void update(Object obj){
        Session session=null;
        try {
            //取得session对象
        	session = HibernateUtils.getSessionFactory().openSession();
            //删除实体
            session.update(obj);
            //提交事务
            session.beginTransaction().commit();
            if(session!=null){
                  session.close();
            }
        } catch (Exception e) {
            session.beginTransaction().rollback();//事务回滚
            if(session!=null){
              session.close();
            }
            e.printStackTrace();
        }
    }
    
    /**
     * 根据指定的hql进行查询，并返回查询结果
     * @param hql，hql语句
     * @return 查询结果
     * @throws Exception
     */
    public List<?> findByHQL(String hql) {
        try {
            Query queryObject = HibernateUtils.getSessionFactory().openSession().createQuery(hql);
            return queryObject.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据指定的实体类型和主键的值，查找实体对象
     * @param cls，实体的类
     * @param key，主键的值
     * @return，查找的实体对象
     * @throws Exception
     */
    public Object findById(Class cls,Serializable key)

    {
        try {
            Object instance = (Object) HibernateUtils.getSessionFactory().openSession().get(cls, key);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public List<?> findByHQL(String hql, HashMap params) {
		 try {
	            Query queryObject = HibernateUtils.getSessionFactory().openSession().createQuery(hql);
	            Iterator iter = params.entrySet().iterator();
	            while (iter.hasNext()) {
	            	Map.Entry entry = (Map.Entry) iter.next();
	            	String key = (String)entry.getKey();
	            	Object val = entry.getValue();
	            	queryObject.setParameter(key, val);
	            }
	            return queryObject.list();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		
	}

	public List<?> findByHQL(String hql, List params) {
		try {
            Query queryObject = HibernateUtils.getSessionFactory().openSession().createQuery(hql);
            if(params.size() > 0) {
            	for(int i=0; i<params.size(); i++) {
            		queryObject.setParameter(i, params.get(i));
            	}
            }
            return queryObject.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
    
    


}
