package com.wang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/8 0008
 */

//保存上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //服务器告诉你，你来的时间，把这个时间封装成 一个信件，你下次带来，我就知道你来了

        //解决中文乱码
        req.setCharacterEncoding("utf-16");
        resp.setCharacterEncoding("utf-16");

        PrintWriter out = resp.getWriter();

        //Cookie,服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//这里返回数组，说明cookie可能村子啊多个

        //判断cookie是否存在
        if(cookies!=null){
            //如果存在怎么办
            out.write("你上一次访问的时间是");

            for (Cookie cookie : cookies) {
                //获取cookie的名字
                if (cookie.getName().equals("LastLoginTime")){
                    //获取cookie中的值
                    long LastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(LastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        }else{
            out.write("这时您第一次访问");
        }

        //服务器给客户端响应一个cookie

        Cookie cookie = new Cookie("LastLoginTime", System.currentTimeMillis()+"");
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
