package com.major.web;

import com.major.model.*;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TheServlet extends HttpServlet {
    public TheServlet(){}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");//request 不清楚是否是UTF-8charset，为了防止乱码，先转换。
        String clan_name = request.getParameter("clan_name");
        String is_api=request.getParameter("api");
        int week = (new GetWeek()).getweek();
        String filename=week+".json";
        String path="/www/server/tomcat/bin/data/";


        (new ObjSaver()).save(clan_name,filename,path);//将数据保存至本地。

        if(is_api==null){//如果是表单

            request.setAttribute("teamlist", new StringReaderI().read(filename, path));//将增加后的对象放进request数据域
            RequestDispatcher dispatcher = request.getRequestDispatcher("showup.jsp");//得到request的调转器，把request关联到jsp
            dispatcher.forward(request, response);}//执行关联，发送。

        else {//如果是api

            String result = (new StringReaderI()).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下POST后的string

        }


    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String teamname = request.getParameter("teamname");//得到队伍名字
        String path="/www/server/tomcat/bin/data/";
        String filename = (new GetWeek()).getweek()+".json";//得到当下文件名


        if(teamname==null){

            String result =(new StringReaderI()).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下POST后的string
        }//如果teamname是空值，那么就直接返回GET


        String is_api=request.getParameter("api");//检查是不是API

        (new ObjDeleter()).delete(filename, path, teamname);//删除操作

        if(is_api==null){
            String return_result = teamname + "  has been defeated!";
            response.setContentType("application/json;");
            PrintWriter out = response.getWriter();
            out.println(return_result);}
        else {

            String result =(new StringReaderI()).read(filename, path);//得到当下文件string
            response.setContentType("application/json;");
            PrintWriter out=response.getWriter();
            out.println(result);//返回当下DELETE后的string
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String callback = request.getParameter("callback");
        String filename = request.getParameter("filename");
        String path="/www/server/tomcat/bin/data/";
        String result = (new StringReaderI()).read(filename, path);
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
