package userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import register.RegisterDao;

import net.sf.json.JSONObject;

import dbutil.JdbcUtils;
public class SearchAction extends HttpServlet {

	private SearchService service;
	/**
	 * Constructor of the object.
	 */
	public SearchAction() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		JSONObject SearchInfo = new JSONObject();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Map<String,Object> map = service.search(params);
		flag=!map.isEmpty()? true:false;
		if (flag==true) {
			SearchInfo.put("picture",map.get("picture"));
			SearchInfo.put("nickname",map.get("nickname"));
			SearchInfo.put("username",map.get("username"));//放入list可以get(0),map里面需要id来获取
			SearchInfo.put("sex",map.get("sex"));
			SearchInfo.put("region",map.get("region"));
			SearchInfo.put("sign",map.get("sign"));
			SearchInfo.put("email",map.get("email"));
			SearchInfo.put("mybackground",map.get("mybackground"));
			out.println(SearchInfo);
		}else{
			SearchInfo.put("Search","fail");
			out.println(SearchInfo);
		}
		out.flush();
		out.close();
	}
	public void init() throws ServletException {//未实例化也会报空指针
		// TODO Auto-generated method stub
		service = new SearchDao();
	}
}
