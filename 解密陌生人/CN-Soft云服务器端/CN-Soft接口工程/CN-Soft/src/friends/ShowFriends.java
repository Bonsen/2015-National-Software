package friends;

import net.sf.json.JSONObject;

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
 * Created by bonsen on 15/5/28.
 */
public class ShowFriends extends HttpServlet {
    private ShowService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        JSONObject ShowFriends = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String ids = request.getParameter("id");
        List<Object> params = new ArrayList<Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int id = Integer.parseInt(ids);
        params.add(id);
        list = service.Show(params);
        flag = !list.isEmpty() ? true : false;
        if (flag == true) {
            ShowFriends.put("ShowFriends",list);
            out.println(ShowFriends);
        }else{
            ShowFriends.put("ShowFriends","none");
            out.println(ShowFriends);
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void init() throws ServletException {// 未实例化也会报空指针
        // TODO Auto-generated method stub
        service = new ShowDao();
    }
}
