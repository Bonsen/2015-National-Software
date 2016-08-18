package userinfo;

import net.sf.json.JSONObject;
import register.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class AlterAction extends HttpServlet {
    private AlterService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        JSONObject Alter = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String sex = request.getParameter("sex");
        String nickname = request.getParameter("nickname");
        String region = request.getParameter("region");
        String sign = request.getParameter("sign");//地址栏参数中文编码问题，设置tomcat8080中URIEncoding="UTF-8"
        String ids = request.getParameter("id");
        Integer it = new Integer(ids);
        int id = it.intValue();

        List<Object> params0 = new ArrayList<Object>();
        List<Object> params1 = new ArrayList<Object>();
        List<Object> params2 = new ArrayList<Object>();
        List<Object> params3 = new ArrayList<Object>();
        List<Object> params4 = new ArrayList<Object>();

        params1.add(nickname);
        params1.add(id);
        params2.add(sex);
        params2.add(id);
        params3.add(region);
        params3.add(id);
        params4.add(sign);
        params4.add(id);

        flag = service.alter(id, nickname, sex, region, sign, params1, params2, params3, params4);
        if (flag == true) {
            Alter.put("Alter", "sucess");
            out.println(Alter);
        }else{
            Alter.put("Alter", "fail");
            out.println(Alter);
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        service = new AlterDao();
    }
}
