package friends;

import dbutil.JdbcUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class DeleteDao implements DeleteService{

    @Override
    public boolean DeleteFriends(List<Object> params0) {
        boolean flag=false;
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        try {
                String sql1 = "delete from friends where mid=? and id=?";
                String sql2 = "delete from friends where id=? and mid=?";
                flag = jdbcUtils.updateByPreparedStatement(sql1, params0);
                flag = jdbcUtils.updateByPreparedStatement(sql2, params0);
        } catch (Exception zz) {
            zz.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
        }
        return flag;
    }
}
