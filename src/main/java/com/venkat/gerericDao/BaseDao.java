package com.venkat.gerericDao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T>  {

public T get(Serializable id);
	
	public List<T> query();
	
	public List<T> query(String whereSql);
		
	public int update(String sql, List<Object> params);
	
	public int update(T t) throws Exception;
	
	public int save(T t) throws Exception;
	
	public int save(String sql, List<Object> params);
	
	public int delete(Serializable id);
	
	public int getCount(String whereSql, Object[] objects);

	
}
