package com.kinsin.servlets;

import com.kinsin.domain.Msg;
import com.kinsin.domain.User;
import com.kinsin.service.MsgService;
import com.kinsin.service.UserService;
import com.kinsin.utils.MD5Util;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getFriends" ,urlPatterns = "/getFriends")
public class getFriends extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();

        String account="";
        account = request.getParameter("account");

        User user=new User(account,"","","",0);
        UserService userService = new UserService();
        MsgService msgService=new MsgService();

        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();

        //先获取所有的好友
        List<User> list=userService.getFriendList(user);
        //循环查询头像、账号、昵称、最近消息、最近时间、未读消息数量
        for (int i = 0; i < list.size(); i++) {
            JSONObject tmpJson=new JSONObject();
//            System.out.println("getFriends_TEST:"+"account1:"+account+"account2:"+list.get(i).getAccount());
            String nick_name=list.get(i).getNick_name();
            String account_friend = list.get(i).getAccount();
            String headicon=list.get(i).getHeadicon();
            Msg msg=msgService.getLastContentAndTime(new Msg(account,list.get(i).getAccount()));
            String last_time="";
            String last_msg="";
            if(msg!=null){
                last_time=msg.getSend_time();
                last_msg=msg.getContent();
            }

            int noReadNumber=msgService.getNoReadNumber(new Msg(account,list.get(i).getAccount()));
//            System.out.println("getFriends_TEST:"+"nick_name:"+nick_name+"/////last_time:"+last_time+"/////last_msg:"+last_msg+"/////number:"+noReadNumber);


            tmpJson.put("account",account_friend);
            tmpJson.put("nick_name",nick_name);
            tmpJson.put("headicon",headicon);
            tmpJson.put("last_time",last_time);
            tmpJson.put("last_msg",last_msg);
            tmpJson.put("noReadNumber",noReadNumber);
            jsonArray.put(tmpJson);
//            System.out.println("getFriends_TEST:"+"jsonArray:"+jsonArray);

        }
        jsonObject.put("list",jsonArray);
//        System.out.println("getFriends_TEST:"+"jsonObject:"+jsonObject);

        if(list!=null){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
