package friends;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dbutil.JdbcUtils;

//数据访问层 class，继承service接口
public class ShowDao implements ShowService {

    @Override
    public List<Map<String, Object>> Show(List<Object> params) {
        String sql = "select id,picture,nickname,username,sex,region,sign,email,mybackground from friends where mid=?";
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findMoreResult(sql, params);
            System.out.println(list);
        } catch (Exception zz) {
            zz.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
        }
        return list;
    }
}
