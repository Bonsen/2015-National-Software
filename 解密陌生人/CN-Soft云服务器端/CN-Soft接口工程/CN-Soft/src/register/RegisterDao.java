package register;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dbutil.JdbcUtils;
import dbutil.JdbcUtilsO;
import register.RegisterService;
//数据访问层 class，继承service接口
public class RegisterDao implements RegisterService {
	
	private JdbcUtils utils = null;
	private JdbcUtilsO utilso = null;

	public RegisterDao() {
		// TODO Auto-generated constructor stub
		utils = new JdbcUtils();
		utilso = new JdbcUtilsO();
	}

	@Override
	//完成对用户注册dao的编写
	public String registerUser(String username,List<Object> params0,List<Object> params1,List<Object> params2,List<Object> params3,List<Object> params00,List<Object> params01) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sure="-1";
		utils.getConnection();
		utilso.getConnection();
		Map<String, Object> map;
		try {
			String sql1 = "select * from user1 where username=?";
			map = utils.findSimpleResult(sql1, params1);
			flag=!map.isEmpty()? true:false;
			if(flag==true){
				sure="1";
				return sure;
			}
			String sql2 = "select * from user1 where phone=?";
			map = utils.findSimpleResult(sql2, params2);
			flag=!map.isEmpty()? true:false;
			if(flag==true){
				sure="2";
				return sure;
			}
			String sql3 = "select * from user1 where email=?";
			map = utils.findSimpleResult(sql3, params3);
			flag=!map.isEmpty()? true:false;
			if(flag==true){
				sure="3";
				return sure;
			}
			String sql00 = "insert into ofuser(username,plainPassword,creationDate,modificationDate)values(?,?,?,?)";
			flag = utilso.updateByPreparedStatement(sql00, params00);

			String sql0 = "insert into user1(username,passwd,phone,email)values(?,?,?,?)";
			flag = utils.updateByPreparedStatement(sql0, params0);
			String sql01 = "select id from user1 where username = ?";
			map = utils.findSimpleResult(sql01, params01);
			sure=map.get("id").toString();
			if(flag==true){
				return sure;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			utils.releaseConn();
			utilso.releaseConn();
		}
		return sure;
		//

		
		//
	}

}
