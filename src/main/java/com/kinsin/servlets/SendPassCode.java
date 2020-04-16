package com.kinsin.servlets;

import com.kinsin.domain.User;
import com.kinsin.service.UserService;
import com.kinsin.utils.MD5Util;
import com.kinsin.utils.SendEmail;
import org.json.JSONObject;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

@WebServlet(name = "sendPassCode" ,urlPatterns = "/sendPassCode")
public class SendPassCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String passCode="",email="";
        passCode = request.getParameter("passCode");
        email = request.getParameter("email");
        JSONObject jsonObject=new JSONObject();
        String judge=null;
        try {
            SendEmail.sendEmail(email,"验证码","您好，您的验证码为 "+passCode+" ,请勿告诉陌生人");
            judge="S";
        } catch (MessagingException e) {
            e.printStackTrace();
            judge="F";
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            judge="F";
        }

        jsonObject.put("RESULT",judge);
        writer.print(jsonObject);

    }
}
