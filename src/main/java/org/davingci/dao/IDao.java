package org.davingci.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface IDao {
	public Object findById(Class cls,Serializable key);
	public void add(Object object);
	public void update(Object object);
	public void delete(Object object);
	public List<?> findByHQL(String hql);
	public List<?> findByHQL(String hql, HashMap params);
	public List<?> findByHQL(String hql, List params);
}
