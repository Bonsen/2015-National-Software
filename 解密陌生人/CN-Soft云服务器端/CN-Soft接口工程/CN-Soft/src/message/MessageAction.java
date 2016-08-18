package message;

import net.sf.json.JSONObject;
import sun.security.util.BigInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/30.
 */
public class MessageAction extends HttpServlet {
    private MessageService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        JSONObject Messages = new JSONObject();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String ids = request.getParameter("id");
        String fids = request.getParameter("fid");
        String times = request.getParameter("time");

        List<Object> params0 = new ArrayList<Object>();
        List<Object> params1 = new ArrayList<Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Integer it = new Integer(ids);
        int id = it.intValue();
        Integer its = new Integer(fids);
        int fid = its.intValue();
        BigInteger time = new BigInteger(times);

        params0.add(id);
        params1.add(fid);

        list = service.Message(id, fid, time, params0, params1);
        flag = !list.isEmpty() ? true : false;
        if (flag == true) {
            Messages.put("Messages",list);
            out.println(Messages);
        } else {
            Messages.put("Messages", "none");
            out.println(Messages);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        service =new MessageDao();
    }

}
