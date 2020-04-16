package com.kinsin.servlets;


import com.kinsin.domain.Msg;
import com.kinsin.service.MsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "setIsRead" ,urlPatterns = "/setIsRead")
public class setIsRead extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String uid1="",uid2="";
        uid1 = request.getParameter("uid1");
        uid2 = request.getParameter("uid2");

        Msg msg=new Msg();
        MsgService msgService=new MsgService();
        //uid1为消息发送人，uid2为消息接收人
        //我已读对方信息，即uid2->uid1
        msg.setUid1(uid2);
        msg.setUid2(uid1);
        msgService.setIsRead(msg);
    }
}
