package com.kinsin.servlets;

import com.kinsin.domain.Msg;
import com.kinsin.domain.User;
import com.kinsin.service.MsgService;
import com.kinsin.service.UserService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "getMsgs" ,urlPatterns = "/getMsgs")
public class getMsgs extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();

        String uid1="",uid2="";
        uid1 = request.getParameter("uid1");
        uid2 = request.getParameter("uid2");

        Msg msg=new Msg();
        msg.setUid1(uid1);
        msg.setUid2(uid2);

        MsgService msgService=new MsgService();

        JSONObject jsonObject=new JSONObject();
        List<Msg> list=msgService.getMsgs(msg);

        jsonObject.put("list",list);
        if(list!=null){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
