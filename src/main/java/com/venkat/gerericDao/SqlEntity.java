package com.venkat.gerericDao;

import java.util.ArrayList;

public class SqlEntity {

	private ArrayList<Object> Params;
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public ArrayList<Object> getParams() {
		return Params;
	}

	public void setParams(ArrayList<Object> params) {
		Params = params;
	}

}
