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

@WebServlet(name = "login" ,urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String username="",password="";
        username = request.getParameter("username");
        password = request.getParameter("password");

        UserService userService = new UserService();

        //MD5加密
        password= MD5Util.MD5EncodeUtf8(password);
        User user = new User(username,password,null,null,0);
        JSONObject jsonObject=new JSONObject();

        int judge=0;
        User temUser = null;
        for (int i = 0; i < 2; i++) {//账号登录和邮箱登录
            temUser = userService.getUserByAccountAndPassword(user);
            if(temUser!=null){
                judge++;
                break;
            }
            temUser = userService.getUserByEmailAndPassword(user);
            if (temUser!=null)
                judge++;
        }

        if(judge!=0){
            //更新登录状态
            userService.updateStatus(new User(temUser.getAccount(),"","","",1));
            //获得用户信息
            User user1=userService.getUserByAccount(new User(temUser.getAccount()));
            //置空密码
            user1.setPassword("");

            jsonObject.put("account",user1.getAccount());
            jsonObject.put("email",user1.getEmail());
            jsonObject.put("nick_name",user1.getNick_name());
            jsonObject.put("net_status",user1.getNet_status());
            jsonObject.put("headicon",user1.getHeadicon());
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
