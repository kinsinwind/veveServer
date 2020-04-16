package com.kinsin.servlets;


import com.kinsin.domain.User;
import com.kinsin.service.UserService;
import com.kinsin.utils.Base64Utils;
import com.kinsin.utils.CurrentTime;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateHeadIcon" ,urlPatterns = "/updateHeadIcon")
public class updateHeadIcon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        String account="",headicon="";
        account = request.getParameter("account");
        headicon = request.getParameter("headicon");
//        try {
//
//        }catch (Exception e){
//            jsonObject.put("RESULT","F");
//            System.out.println(e.getMessage());
//        }

        StringBuffer stringBuffer=new StringBuffer(headicon);//将base64编码存入内存
        String name=account+ CurrentTime.getTimestamp()+".jpg";//拼接名字
            Base64Utils.GenerateImage(stringBuffer.toString(),"/usr/headicon/"+name);//将图片解码存入硬盘
//        Base64Utils.GenerateImage(stringBuffer.toString(),"D:\\testImg\\"+name);//将图片解码存入硬盘
            String url = "http://101.201.199.161:8080/img/"+name;//拼接图片地址
//        String url = "http://192.168.1.102:8080/img/"+name;//拼接图片地址
        User user=new User();
        user.setAccount(account);
        user.setHeadicon(url);
        UserService userService=new UserService();

        if(userService.updateHeadIcon(user)){
            jsonObject.put("RESULT","S");
            jsonObject.put("url",url);
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);
    }
}
