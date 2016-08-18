package userinfo;

import dbutil.JdbcUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class AlterDao implements AlterService{


    @Override
    public boolean alter(int id, String nickname,String sex,String region,String sign,
                                        List<Object> params1,List<Object> params2,List<Object> params3,List<Object> params4) {
        // TODO Auto-generated method stub
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        boolean flag =false;
        // 不同于ArrayList<HashMap<String, Object>>?????????????
        try {

            if(nickname!=null){
                String sql1 = "update userinfo set nickname=? where id=?";
                flag=jdbcUtils.updateByPreparedStatement(sql1, params1);
            }
            if(sex!=null){
                String sql2 = "update userinfo set sex=? where id=?";
                flag=jdbcUtils.updateByPreparedStatement(sql2, params2);
            }
            if(region!=null){
                String sql3 = "update userinfo set region=? where id=?";
                flag=jdbcUtils.updateByPreparedStatement(sql3, params3);
            }
            if(sign!=null){
                String sql4 = "update userinfo set sign=? where id=?";
                flag=jdbcUtils.updateByPreparedStatement(sql4, params4);
            }
        } catch (Exception zz) {
            zz.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
        }

        return flag;
    }
}
