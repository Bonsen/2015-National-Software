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
public class DeleteAction extends HttpServlet {
    private DeleteService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        JSONObject DeleteFriend = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String mids = request.getParameter("id");
        String ids = request.getParameter("fid");
        int mid = Integer.parseInt(mids);
        int id = Integer.parseInt(ids);

        List<Object> params0 = new ArrayList<Object>();


        params0.add(mid);
        params0.add(id);
        flag = service.DeleteFriends(params0);
        if (flag==true) {
            DeleteFriend.put("DeleteFriend","success");
            out.println(DeleteFriend);
        }else{
            DeleteFriend.put("DeleteFriend","fail");
            out.println(DeleteFriend);
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public void init() throws ServletException {// 未实例化也会报空指针
        // TODO Auto-generated method stub
        service = new DeleteDao();
    }
}
