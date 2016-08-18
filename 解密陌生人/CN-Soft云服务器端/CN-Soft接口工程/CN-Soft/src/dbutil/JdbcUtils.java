package dbutil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class JdbcUtils {
	private final String USERNAME = "okmk741851";
	private final String PASSWORD = "123456z";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://xinqilvxing.mysql.rds.aliyuncs.com:3306/ocia?characterEncoding=utf8";
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	//实现批处理操作的功能
	private Statement stmt;
	public JdbcUtils() {
		try {
			Class.forName(DRIVER);
			System.out.println("注册驱动成功");
		} catch (Exception e) {

		}
	}

	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("注册驱动成功2");
		} catch (Exception e) {
		}
		return connection;

	}

	//批处理操作
	public boolean deleteByBatch(String[] sql)throws SQLException{
		boolean flag =false;
		stmt=connection.createStatement();
		if(sql!=null){//注意if不能用长度判断数组
			for(int i=0;i<sql.length;i++){
				stmt.addBatch(sql[i]);
			}
		}
		int []count=stmt.executeBatch();
		if(count !=null){
			flag=true;
		}
		System.out.println("注册驱动成功3");
		return flag;
	}
	
	
	
	
	
	
	
	// 完成数据库的表的添加删除和修改的操作
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// 表示影响数据库的行数
		pstmt = connection.prepareStatement(sql);
		
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// ??
			}
		}
		result = pstmt.executeUpdate();
		System.out.println(result);
		flag = result > 0 ? true : false;
		return flag;
	}

	// 查询返回单条记录
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// ??
			}
		}
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();// 获得列的名称
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	// 查询返回多条记录
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// ??
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	// jdbc的封装可以用反射机制来封装
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws SQLException, InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// ??
			}
		}
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();// 获得列的名称
		while (resultSet.next()) {
			// 通过反射机制创建实例
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);// 打开javabean的访问 private权限
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;
	}

	// 通过反射机制范文数据库
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// ??
			}
		}
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();// 获得列的名称
		while (resultSet.next()) {
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		if (connection != null) {
			try {
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();*/
		/*
		 * String sql = "insert into user(username,passwd)values(?,?)";
		 * List<Object> params = new ArrayList<Object>(); params.add("rose");
		 * params.add("123");
		 * 
		 * try { boolean flag = jdbcUtils.updateByPreparedStatement(sql,
		 * params); System.out.println(flag); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */

		/*
		 * 查询单条记录 String sql= "select * from user where username=?";
		 * List<Object>params = new ArrayList<Object>(); params.add("rose");
		 * try{ Map<String,Object> map = jdbcUtils.findSimpleResult(sql,
		 * params); System.out.println(map); }catch(Exception e){
		 * 
		 * } } }
		 */

		/*
		 * 查询多条记录 String sql = "select * from user"; try { List<Map<String,
		 * Object>> map = jdbcUtils.findMoreResult(sql, null);
		 * System.out.println(map); } catch (Exception e) {
		 * 
		 * }finally{ jdbcUtils.releaseConn(); }
		 */

		/* 反射机制-单条-与UserInfo对应
		String sql = "select * from user where username=?";
		List<Object> params = new ArrayList<Object>();
		params.add("rose");
		try {
			UserInfo userInfo = jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
			System.out.println(userInfo.toString());
		} catch (Exception e) {

		}finally{
			jdbcUtils.releaseConn();
		}*/
		
		//反射机制-多条
		
	/*	String sql = "select * from user";
		try {
			List<UserInfo> list = jdbcUtils.findMoreRefResult(sql, null, UserInfo.class);
			System.out.println(list);
		} catch (Exception e) {

		}finally{
			jdbcUtils.releaseConn();
		}
	}*/
}

/**
 * @param args
 */

