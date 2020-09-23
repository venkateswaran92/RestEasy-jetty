package com.venkat.gerericDao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class BaseDaoImpl<T> implements BaseDao<T> {

	JdbcTemplate jdbcTemplate = Factory.getJdbcTemplate();
	protected Class<T> entityClass = null;
	private RowMapper<T> rowMapper = null;

	@SuppressWarnings("unchecked")
	public Class<T> setClass(String className) throws ClassNotFoundException {
		entityClass = (Class<T>) Class.forName(className);
		rowMapper = new BeanPropertyRowMapper<T>(entityClass);
		return entityClass;
	}

	/**
	 * Get entity
	 * 
	 * @param id
	 *            object id (Serializable)
	 * @return T object
	 */
	@Override
	public T get(Serializable id) {
		String sql = getSql() + "and id=? ";
		return (T) jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	/**
	 * @return List<T>
	 */
	@Override
	public List<T> query() {
		return (List<T>) jdbcTemplate.query(getSql(), rowMapper);
	}

	/**
	 * @param page
	 *            paging parameters
	 * @param whereSql
	 *            query condition (example: o.name=?)
	 * @param params
	 *            The parameter corresponding to the query condition
	 *            (List<Object>)
	 * @return List<T>
	 */
	@Override
	public List<T> query(String whereSql) {
		String sql = getSqlWhere(whereSql);
		return (List<T>) jdbcTemplate.query(sql, rowMapper);
	}

	/**
	 * Update
	 * 
	 * @param sql
	 *            custom update sql
	 * @param params
	 *            The parameter corresponding to the query condition
	 *            (List<Object>)
	 * @return int number of updates
	 */
	@Override
	public int update(String sql, List<Object> params) {
		// String sql="update person set name=? where id=?";
		return jdbcTemplate.update(sql, params.toArray());
	}

	/**
	 * Update (first retrieved from the database to update)
	 * 
	 * @param t
	 *            updated object
	 * @return int number of updates
	 */
	@Override
	public int update(T t) throws Exception {
		SqlEntity sqlEntity = getUpdateSql(t);
		// System.out.println("=====sqlEntity.getSql()="+sqlEntity.getSql());
		return jdbcTemplate.update(sqlEntity.getSql(), sqlEntity.getParams().toArray());
	}

	/**
	 * Save
	 * 
	 * @param t
	 *            saved object
	 * @return int number of saves
	 */
	@Override
	public int save(T t) throws Exception {
		SqlEntity sqlEntity = getSaveSql(t);
		return jdbcTemplate.update(sqlEntity.getSql(), sqlEntity.getParams().toArray());
	};

	/**
	 * Save
	 * 
	 * @param sql
	 *            custom save sql
	 * @param params
	 *            The parameter corresponding to the query condition
	 *            (List<Object>)
	 * @return int number of saves
	 */
	@Override
	public int save(String sql, List<Object> params) {
		// String sql="INSERT INTO employee (`id`,name,address)
		// VALUES(?,?,?);";
		return jdbcTemplate.update(sql, params.toArray());
	}

	/**
	 * delete
	 * 
	 * @param id
	 *            object id (Serializable)
	 * @return int number of deletions
	 */
	@Override
	public int delete(Serializable id) {
		String sql = "delete from " + StrUtils.changeName(this.entityClass.getSimpleName()) + " where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getCount(String whereSql, Object[] objects) {
		String entityName = this.entityClass.getSimpleName();
		StringBuffer sql = new StringBuffer("select count(*) from ");
		sql.append(StrUtils.changeName(entityName));
		sql.append(" o ").append(whereSql);
		return jdbcTemplate.queryForInt(sql.toString(), objects);

	}

	protected String getSql() {
		String entityName = this.entityClass.getSimpleName();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(StrUtils.changeName(entityName));
		sql.append(" o where 1=1 ");
		return sql.toString();
	}

	protected String getSql(String whereSql) {
		String entityName = this.entityClass.getSimpleName();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(StrUtils.changeName(entityName));
		sql.append(" o where 1=1 ");
		if (!StrUtils.isEmpty(whereSql)) {
			sql.append(" ").append(whereSql);
		}
		return sql.toString();
	}

	/**
	 * Get sql
	 * 
	 * @param whereSql
	 *            query condition parameter
	 * @return sql
	 */
	protected String getSqlWhere(String whereSql) {
		String entityName = this.entityClass.getSimpleName();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(StrUtils.changeName(entityName));
		if (!StrUtils.isEmpty(whereSql)) {
			sql.append(" ").append(whereSql);
		}
		return sql.toString();
	}

	private SqlEntity getUpdateSql(T t) throws Exception {
		SqlEntity sqlEntity = new SqlEntity();
		sqlEntity.setParams(new ArrayList<Object>());
		Field[] fields = entityClass.getDeclaredFields();
		StringBuffer sql = new StringBuffer("");
		sql.append("update ").append(StrUtils.changeName(entityClass.getSimpleName())).append(" o set ");
		for (Field field : fields) {
			StringBuffer methodName = new StringBuffer("");
			// System.out.println("===field.getType()="+field.getType());
			if (field.getType() == boolean.class) {
				if (field.getName().contains("is")) {
					methodName.append(field.getName());
				} else {
					methodName.append("is").append(StrUtils.firstCodeToUpperCase(field.getName()));
				}
			} else {
				methodName.append("get").append(StrUtils.firstCodeToUpperCase(field.getName()));
			}
			if (!"id".equals(field.getName())) {
				Method method = entityClass.getMethod(methodName.toString(), new Class[] {});
				Object objectValue = method.invoke(t, new Object[] {});
				sqlEntity.getParams().add(objectValue);
				sql.append(" o.").append(StrUtils.changeName(field.getName())).append("= ?,");
			}
		}
		if (sql.indexOf(",") > -1) {
			sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(" where o.id=?");
		Method idMethod = entityClass.getMethod("getId", new Class[] {});
		sqlEntity.getParams().add(idMethod.invoke(t, new Object[] {}));
		sqlEntity.setSql(sql.toString());
		return sqlEntity;
	}

	private SqlEntity getSaveSql(T t) throws Exception {
		SqlEntity sqlEntity = new SqlEntity();
		sqlEntity.setParams(new ArrayList<Object>());
		Field[] fields = entityClass.getDeclaredFields();
		StringBuffer sql = new StringBuffer("");
		sql.append("insert into ").append(StrUtils.changeName(entityClass.getSimpleName())).append(" ( ");
		int paramLength = 0;
		for (Field field : fields) {
			StringBuffer methodName = new StringBuffer("");
			if (field.getType() == boolean.class) {
				if (field.getName().contains("is")) {
					methodName.append(field.getName());
				} else {
					methodName.append("is").append(StrUtils.firstCodeToUpperCase(field.getName()));
				}
			} else {
				methodName.append("get").append(StrUtils.firstCodeToUpperCase(field.getName()));
			}
			Method method = entityClass.getMethod(methodName.toString(), new Class[] {});
			Object value = method.invoke(t, new Object[] {});
			if (!StrUtils.isEmpty(value)) {
				sqlEntity.getParams().add(value);
				sql.append("`").append(StrUtils.changeName(field.getName())).append("`").append(",");
				paramLength++;
			}
		}
		if (sql.indexOf(",") > -1) {
			sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(") values(");
		for (int i = 0; i < paramLength; i++) {
			sql.append("?,");
		}
		if (sql.indexOf(",") > -1) {
			sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(")");
		sqlEntity.setSql(sql.toString());
		return sqlEntity;
	}

}