package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import register.RegisterDao;
import register.RegisterService;
//控制层 servlet
public class RegisterAction extends HttpServlet {

	private RegisterService service;
	/**
	 * Constructor of the object.
	 */
	public RegisterAction() {
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
		String sure="-1";
		JSONObject Register = new JSONObject();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String passwd=request.getParameter("passwd");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		List<Object>params0 =new ArrayList<Object>();
		List<Object>params1 =new ArrayList<Object>();
		List<Object>params2 =new ArrayList<Object>();
		List<Object>params3 =new ArrayList<Object>();
		List<Object>params00 =new ArrayList<Object>();
		List<Object>params01 =new ArrayList<Object>();
		params0.add(username);
		params0.add(passwd);
		params0.add(phone);
		params0.add(email);
		params1.add(username);
		params2.add(phone);
		params3.add(email);
		params00.add(username);
		params00.add(passwd);
		params00.add("1");
		params00.add("1");
		params01.add(username);
		sure= service.registerUser(username,params0,params1,params2,params3,params00,params01);
		if(sure.equals("1")){
			Register.put("Register", "1");
			out.println(Register);
		}else if(sure.equals("2")){
			Register.put("Register", "2");
			out.println(Register);
		}else if(sure.equals("3")){
			Register.put("Register", "3");
			out.println(Register);
		}else{
			Register.put("Register", "0");
			Register.put("id",sure);
			out.println(Register);
		}
		out.flush();
		out.close();
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		service = new RegisterDao();
	}

}
