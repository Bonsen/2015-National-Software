package upload;

import dbutil.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class UploadDao implements UploadService{
    @Override
    //完成对用户注册dao的编写
    public boolean kupload(int up,List<Object> params) {
        // TODO Auto-generated method stub
        boolean flag = false;
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        try {
            if(up==1){
                String sql0 = "update userinfo set picture=? where id=?";
                flag = jdbcUtils.updateByPreparedStatement(sql0, params);
            }else if(up==2){
                String sql1 = "update userinfo set mybackground=? where id=?";
                flag = jdbcUtils.updateByPreparedStatement(sql1, params);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            //关闭数据库连接
            jdbcUtils.releaseConn();
        }
        return flag;
        //

    }
}
