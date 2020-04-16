package com.kinsin.servlets;

import com.kinsin.domain.User;
import com.kinsin.service.UserService;
import com.kinsin.utils.MD5Util;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "accountJudge" ,urlPatterns = "/accountJudge")
public class AccountJudge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String username="";
        username = request.getParameter("account");

        UserService userService = new UserService();

        User user = new User(username,null,null,null,0);
        JSONObject jsonObject=new JSONObject();

        if(userService.getUserByAccount(user)==null){//是否可以使用
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
