package login;

import java.util.List;
import java.util.Map;

import dbutil.JdbcUtils;

import login.LoginService;

public class LoginDao implements LoginService {

	private JdbcUtils jdbcUtils =null;
	public LoginDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public Map<String,Object> login(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from user1 where username=? and passwd = ?";
		Map<String,Object> map =null;
		try {
			jdbcUtils.getConnection();
			map =jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			jdbcUtils.releaseConn();
		}
		return map;
	}

}
