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

@WebServlet(name = "register" ,urlPatterns = "/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account="",password="",nick_name="",email="";
        account = request.getParameter("account");
        password = request.getParameter("password");
        nick_name=request.getParameter("nick_name");
        email=request.getParameter("email");

        UserService userService = new UserService();

        //MD5加密
        password= MD5Util.MD5EncodeUtf8(password);
        User user = new User(account,password,email,nick_name,0);
        JSONObject jsonObject=new JSONObject();

        if(userService.addUser(user)){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
