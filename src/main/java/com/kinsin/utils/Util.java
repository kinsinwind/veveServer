package com.kinsin.utils;

import org.apache.ibatis.session.SqlSession;

import java.util.ResourceBundle;

public class Util {

    public static SqlSession sqlSession;

    public static String getDBByKey(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com/kinsin/config/db");
        return  resourceBundle.getString(key);
    }
}
