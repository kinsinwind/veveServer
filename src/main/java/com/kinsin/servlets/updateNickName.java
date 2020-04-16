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

@WebServlet(name = "updateNickName" ,urlPatterns = "/updateNickName")
public class updateNickName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account="",nickname="";
        account = request.getParameter("account");
        nickname = request.getParameter("nickname");
        User user=new User();
        user.setAccount(account);
        user.setNick_name(nickname);
        UserService userService=new UserService();

        JSONObject jsonObject=new JSONObject();
        if(userService.updateNickName(user)){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);
    }
}
