package com.kinsin.linstener; /**
 * createBy 林超 on 2019/7/17 15:15
 *
 * @direction
 */

import com.kinsin.utils.Util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.Reader;

@WebListener()
public class startUP implements ServletContextListener {
    public startUP() {
    }
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("项目开始运行");
        String src = "MyBatis-config.xml";
        //加载资源文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建连接工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        //获得连接
        Util.sqlSession = sqlSessionFactory.openSession();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("项目结束运行");
    }
}
