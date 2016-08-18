package friends;

import dbutil.JdbcUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class AddDao implements AddService {

    @Override
    public boolean AddFriends(int mid,int id,List<Object> params0,List<Object> params1,List<Object> params2,List<Object> params3,List<Object> params4) {
        boolean flag=false;
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        Map<String,Object> map =null;
        Map<String,Object> map1 =null;
        try {
            String sql2 = "select count(*) from friends where mid=? and id=?";
            map =jdbcUtils.findSimpleResult(sql2, params2);
            System.out.println(map.toString());
            String num=map.get("count(*)").toString();
            System.out.println(num);
            if(num.equals("0")){
                String sql0 = "select picture,nickname,username,sex,region,sign,email,mybackground from userinfo where id=?";
                map =jdbcUtils.findSimpleResult(sql0, params0);
                params1.add(map.get("picture"));
                params1.add(map.get("nickname"));
                params1.add(map.get("username"));
                params1.add(map.get("sex"));
                params1.add(map.get("region"));
                params1.add(map.get("sign"));
                params1.add(map.get("email"));
                params1.add(map.get("mybackground"));
                String sql1 = "insert into friends(mid,id,picture,nickname,username,sex,region,sign,email,mybackground)values(?,?,?,?,?,?,?,?,?,?)";
                flag = jdbcUtils.updateByPreparedStatement(sql1, params1);
            }else {
                return flag;
            }

            String sql3 = "select count(*) from friends where id=? and mid=?";
            map1 =jdbcUtils.findSimpleResult(sql3, params2);
            System.out.println(map.toString());
            String num1=map1.get("count(*)").toString();
            System.out.println(num);
            if(num1.equals("0")){
                String sql00 = "select picture,nickname,username,sex,region,sign,email,mybackground from userinfo where id=?";
                map1 =jdbcUtils.findSimpleResult(sql00, params3);
                params4.add(map1.get("picture"));
                params4.add(map1.get("nickname"));
                params4.add(map1.get("username"));
                params4.add(map1.get("sex"));
                params4.add(map1.get("region"));
                params4.add(map1.get("sign"));
                params4.add(map1.get("email"));
                params4.add(map1.get("mybackground"));
                String sql11 = "insert into friends(mid,id,picture,nickname,username,sex,region,sign,email,mybackground)values(?,?,?,?,?,?,?,?,?,?)";
                flag = jdbcUtils.updateByPreparedStatement(sql11, params4);
            }else{
                return flag;
            }


        } catch (Exception zz) {
            zz.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
        }
        return flag;
    }
}
