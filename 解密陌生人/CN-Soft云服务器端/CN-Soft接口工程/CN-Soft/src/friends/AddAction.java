package friends;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public class AddAction extends HttpServlet {
    private AddService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        JSONObject AddFriend = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String mids = request.getParameter("id");
        String ids = request.getParameter("fid");
        int mid = Integer.parseInt(mids);
        int id = Integer.parseInt(ids);

        List<Object> params0 = new ArrayList<Object>();
        List<Object> params1 = new ArrayList<Object>();
        List<Object> params2 = new ArrayList<Object>();
        List<Object> params3 = new ArrayList<Object>();
        List<Object> params4 = new ArrayList<Object>();
        params0.add(id);
        params1.add(mid);
        params1.add(id);
        params2.add(mid);
        params2.add(id);
        params3.add(mid);
        params4.add(id);
        params4.add(mid);
        flag = service.AddFriends(mid, id, params0, params1,params2,params3,params4);
        if (flag==true) {
            AddFriend.put("AddFriend","success");
            out.println(AddFriend);
        }else{
            AddFriend.put("AddFriend","fail");
            out.println(AddFriend);
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public void init() throws ServletException {// 未实例化也会报空指针
        // TODO Auto-generated method stub
        service = new AddDao();
    }
}
