package map;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbutil.JdbcUtils;

public class MapDao implements MapService {
	private int count = 0;
	private double EARTH_RADIUS = 6378.137;// 地球半径

	public MapDao() {
		count = 0;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// JdbcUtils jdbcUtils = new JdbcUtils();
		// // TODO Auto-generated method stub
		// String sql = "select longitude,latitude from map";
		// jdbcUtils.getConnection();
		// try { List<Map<String,Object>> map = jdbcUtils.findMoreResult(sql,
		// null);
		// System.out.println(map); } catch (Exception e) {
		// e.printStackTrace();
		// }finally{ jdbcUtils.releaseConn(); }
	}

	@Override
	public List<Map<String, Object>> map(double longitude, double latitude,
			int id, List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from map";
		JdbcUtils jdbcUtils = new JdbcUtils();
		String a = null;
		String b = null;
		String c = null;
		String d = null;
		String e = null;
		String f = null;
		String g = null;
		String h = null;
		String j = null;
		String k = null;
		String l = null;
		jdbcUtils.getConnection();
		List<Map<String, Object>> list = null;
		double distance = 0.0;
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		// 不同于ArrayList<HashMap<String, Object>>?????????????
		try {
			String sql0 = "update map set longitude=?,latitude=? where id=?";
			jdbcUtils.updateByPreparedStatement(sql0, params);
			
			list = jdbcUtils.findMoreResult(sql, null);
			System.out.println(list);
			Map<String, Object> map1 = new HashMap<String, Object>();
			count = 0; // 函数内要清零
			for (int i = 0; i < list.size(); i++) {
				double longitude2=Double.parseDouble(list.get(i).get("longitude").toString());
				System.out.println(longitude2);
				double latitude2=Double.parseDouble(list.get(i).get("latitude").toString());
				System.out.println(latitude2);
				distance = getDistance(longitude, latitude, longitude2,
						latitude2);
				System.out.println(distance);
				System.out.println("");
				if (distance <= 1&&distance!=0) {
					a = count + "";
					b = count + "t";
					c = count + "longitude";
					d = count + "latitude";
					e = count + "sex";
					f = count + "picture";
					g = count + "nickname";
					h = count + "region";
					j = count + "sign";
					k = count + "email";
					l = count + "mybackground";
					map1.put(a, list.get(i).get("id"));
					// System.out.println(map1.get(i+""));
					map1.put(b, distance);
					map1.put(c, longitude2);
					map1.put(d, latitude2);
					map1.put(e, list.get(i).get("sex"));
					map1.put(f, list.get(i).get("picture"));
					map1.put(g, list.get(i).get("nickname"));
					map1.put(h, list.get(i).get("region"));
					map1.put(j, list.get(i).get("sign"));
					map1.put(k, list.get(i).get("email"));
					map1.put(l, list.get(i).get("mybackground"));
					// System.out.println(map1.get(i+"t"));
					count++;
					System.out.println(count);
				}
			}
			list1.add(map1);
			for (int i = 0; i < count; i++) {
				a = i + "";
				b = i + "t";
				// System.out.println(list1.get(0));
				// System.out.println(list1.get(0).size());
				// System.out.println(list1.get(0).get(a));
				// System.out.println(list1.get(0).get(b));
			}
		} catch (Exception zz) {
			zz.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}

		return list1;
	}

	@Override
	public int getcount() {
		return count;
	}

	// 公里为单位
	private double getDistance(double longitude1, double latitude1,
			double longitude2, double latitude2) {

		double radLat1 = rad(latitude1);
		double radLat2 = rad(latitude2);
		double a = radLat1 - radLat2;
		double b = rad(longitude1) - rad(longitude2);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}

}
