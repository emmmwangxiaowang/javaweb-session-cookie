package com.wang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author 王航
 * @QQ 954544828
 * @since 2021/4/8 0008
 */
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决中文乱码
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");


        //Cookie,服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//这里返回数组，说明cookie可能村子啊多个

        PrintWriter out = resp.getWriter();
        //判断cookie是否存在
        if(cookies!=null){
            //如果存在怎么办
            out.write("你上一次访问的时间是");

            for (Cookie cookie : cookies) {
                //获取cookie的名字
                if (cookie.getName().equals("name")){
                    //解码
                    System.out.println(cookie.getValue());
                }
            }
        }else{
            out.write("这时您第一次访问");
        }

        //编码
        Cookie cookie = new Cookie("name", "王小王");
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
