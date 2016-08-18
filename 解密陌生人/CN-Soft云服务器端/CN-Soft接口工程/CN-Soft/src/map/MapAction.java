package map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import register.RegisterDao;

import net.sf.json.JSONObject;

import userinfo.SearchDao;
import userinfo.SearchService;

public class MapAction extends HttpServlet {
	private MapService service;

	/**
	 * Constructor of the object.
	 */
	public MapAction() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		int count = 0;
		JSONObject SearchInfo = new JSONObject();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String longitudes = request.getParameter("longitude");
		String latitudes = request.getParameter("latitude");
		String ids = request.getParameter("id");
		List<Object> params = new ArrayList<Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		double longitude = Double.parseDouble(longitudes);
		double latitude = Double.parseDouble(latitudes);
		Integer it = new Integer(ids);
		int id = it.intValue();
		params.add(longitude);
		params.add(latitude);
		params.add(id);
		list = service.map(longitude, latitude, id, params);
		count = service.getcount();
		flag = !list.isEmpty() ? true : false;
		Map<String, Object> map2 = null;
		if (flag == true) {
			System.out.println(count);
			for (int i = 0; i < count; i++) {
				map2 = new HashMap<String, Object>();
				map2.put("id", list.get(0).get(i + ""));
				// System.out.println(list.get(0).get(i+""));
				map2.put("distance", list.get(0).get(i + "t"));
				map2.put("longitude", list.get(0).get(i + "longitude"));
				map2.put("latitude", list.get(0).get(i + "latitude"));
				map2.put("sex", list.get(0).get(i + "sex"));
				map2.put("picture", list.get(0).get(i + "picture"));
				map2.put("nickname", list.get(0).get(i + "nickname"));
				map2.put("region", list.get(0).get(i + "region"));
				map2.put("sign", list.get(0).get(i + "sign"));
				map2.put("email", list.get(0).get(i + "email"));
				map2.put("mybackground", list.get(0).get(i + "mybackground"));
				// System.out.println(list.get(0).get(i+"t"));
				list2.add(map2);
			}
			if (count != 0) {
				SearchInfo.put("addfriends", list2);
				out.println(SearchInfo);
			}
			if (count == 0) {
				SearchInfo.put("1-Round", "none");
				out.println(SearchInfo);
			}

		}

		out.flush();
		out.close();
	}

	public void init() throws ServletException {// 未实例化也会报空指针
		// TODO Auto-generated method stub
		service = new MapDao();
	}

}
