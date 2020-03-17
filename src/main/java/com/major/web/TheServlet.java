package com.major.web;

import com.google.gson.Gson;
import com.major.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
@RequestMapping(path = "/*")
public class TheServlet  {
    public TheServlet(){

    }
    @RequestMapping(method = {RequestMethod.POST})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");//request 不清楚是否是UTF-8charset，为了防止乱码，先转换。
        String clan_name = request.getParameter("clan_name");
        String is_api=request.getParameter("api");
/**
 * 下面注释注入一些bean
 */
        ApplicationContext ac=new AnnotationConfigApplicationContext(Configuration.class);
        GetWeek GW=(GetWeek)ac.getBean("getWeek");
        StringReaderI strR=(StringReaderI)ac.getBean("stringReaderI");

        ObjSaver OBS=(ObjSaver)ac.getBean("objSaver");
        int week = (GW).getweek();//BEANS 注解

        String filename=week+".json";
        String path="/www/server/tomcat/bin/data/";


        (OBS).save(clan_name,filename,path);//将数据保存至本地。

        if(is_api==null){//如果是表单

            request.setAttribute("teamlist", strR.read(filename, path));//将增加后的对象放进request数据域
            RequestDispatcher dispatcher = request.getRequestDispatcher("showup.jsp");//得到request的调转器，把request关联到jsp
            dispatcher.forward(request, response);}//执行关联，发送。

        else {//如果是api

            String result = (strR).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下POST后的string

        }


    }

    @RequestMapping(method = {RequestMethod.DELETE})
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /**
         * 下面是注解加入beans
         */
        ApplicationContext ac=new AnnotationConfigApplicationContext(Configuration.class);
        GetWeek GW=(GetWeek)ac.getBean("getWeek");
        StringReaderI strR=(StringReaderI)ac.getBean("stringReaderI");
        ObjDeleter OBD=(ObjDeleter)ac.getBean("objDeleter");

        /**
         * 上面是注解加入beans
         */
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String teamname = request.getParameter("teamname");//得到队伍名字
        String path="/www/server/tomcat/bin/data/";
        String filename = (GW).getweek()+".json";//得到当下文件名


        if(teamname==null){

            String result =(strR).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下POST后的string
        }//如果teamname是空值，那么就直接返回GET


        String is_api=request.getParameter("api");//检查是不是API

        (OBD).delete(filename, path, teamname);//删除操作

        if(is_api==null){
            String return_result = teamname + "  has been defeated!";
            response.setContentType("application/json;");
            PrintWriter out = response.getWriter();
            out.println(return_result);}
        else {

            String result =(strR).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下DELETE后的string
        }

    }
    @RequestMapping(method = {RequestMethod.GET})
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /**
         * 下面是注解加入beans
         */
        ApplicationContext ac=new AnnotationConfigApplicationContext(Configuration.class);

        StringReaderI strR=(StringReaderI)ac.getBean("stringReaderI");


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String callback = request.getParameter("callback");
        String filename = request.getParameter("filename");
        String path="/www/server/tomcat/bin/data/";
        
        String result = (strR).read(filename, path);
        if(callback==null){

            response.setContentType("application/json;");
            PrintWriter out = response.getWriter();
            out.println(result);}

        else {

            String return_result = callback + "(" + result + ")" + ";";
            response.setContentType("text/javascript;");
            PrintWriter out = response.getWriter();//用于传输数据至响应报文中
            out.println(return_result);}






    }





}

