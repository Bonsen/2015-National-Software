package userinfo;

import java.util.List;
import java.util.Map;

import dbutil.JdbcUtils;


public class SearchDao implements SearchService {

	private JdbcUtils jdbcUtils =null;
	public SearchDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	public Map<String,Object> search(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag =false;
		String sql = "select * from userinfo where id=?";
		try {
			jdbcUtils.getConnection();
			Map<String,Object> map =jdbcUtils.findSimpleResult(sql, params);
			flag=!map.isEmpty()? true:false;
			if(flag==true){
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			jdbcUtils.releaseConn();
		}
		return null ;
	}

}
