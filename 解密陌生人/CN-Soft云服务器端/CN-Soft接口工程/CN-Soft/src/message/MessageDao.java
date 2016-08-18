package message;
import dbutil.JdbcUtils;
import dbutil.JdbcUtilsO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/30.
 */
public class MessageDao implements MessageService{
    @Override
    public List<Map<String, Object>> Message (int id,int fid ,BigInteger time, List<Object> params0,List<Object> params1) {
        // TODO Auto-generated method stub
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        JdbcUtilsO jdbcUtilso = new JdbcUtilsO();
        jdbcUtilso.getConnection();

        Map<String,Object> map =null;
        Map<String,Object> fmap =null;
        Map<String,Object> zmap =null;
        List<Map<String, Object>> list0 = null;
        List<Map<String, Object>> list1 = null;
        // 不同于ArrayList<HashMap<String, Object>>?????????????
        try {
            String sql0 = "select username from user1 where id =?";
            map =jdbcUtils.findSimpleResult(sql0, params0);
            String username=map.get("username").toString()+"@120.24.76.184";
            System.out.println(username);

            String sql1 = "select username from user1 where id =?";
            fmap =jdbcUtils.findSimpleResult(sql1, params1);
            String fusername=fmap.get("username").toString()+"@120.24.76.184";
            System.out.println(fusername);

            List<Object> params2 = new ArrayList<Object>();
            List<Object> params22 = new ArrayList<Object>();
            params2.add(username);
            params2.add(fusername);
            String sql2 = "select conversationId from archiveconversations where ownerJid=? and withJid=?";
            list0 = jdbcUtilso.findMoreResult(sql2, params2);

            for(int i=0;i<list0.size();i++){
                BigInteger cId = new BigInteger(list0.get(i).get("conversationId").toString());
                params22 = new ArrayList<Object>();
                String sql22 = "update archivemessages set ownerJid=?,withJid=? where ownerJid=? and withJid=? and conversationId=?";
                params22.add(username);
                params22.add(fusername);
                params22.add("1");
                params22.add("1");
                params22.add(cId);
                jdbcUtilso.updateByPreparedStatement(sql22, params22);
            }

//            List<Object> params2 = new ArrayList<Object>();
//            params2.add("1");
//            params2.add(direction);
//            params2.add(time);
//            String sql2 = "select time,body from archivemessages where ownerJid=? and direction=? and time<=? LIMIT 0,30";
//            list0 = jdbcUtilso.findMoreResult(sql2, params2);
//            System.out.println(list0);

            List<Object> params3 = new ArrayList<Object>();
            params3.add(time);
            params3.add(username);
            params3.add(fusername);
            params3.add("to");
            String sql3 = "select time,body from archivemessages where time<=? and ownerJid=? and withJid=? and direction=? LIMIT 0,30";
            list1 = jdbcUtilso.findMoreResult(sql3, params3);
            System.out.println(list1);
        }
         catch (Exception zz) {
            zz.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
            jdbcUtilso.releaseConn();
        }
        return list1;
}}
