package com.major.model;

import com.google.gson.Gson;

import java.io.*;

public class ObjDeleter {
    public ObjDeleter() {
    }//无参构造器，无论如何都得有构造器

    public static void delete(String filename, String path,String name_id) throws IOException {

        String readresult=(new StringReaderI()).read(filename, path);//这一部分应当通过注解来自动new类

        //以上为读操作---------------------------------------------------------------------重复代码，可以用我写的StringReader方法

        Gson gson = new Gson();//这个最好通过注解自动new
        Teams readed = gson.fromJson(readresult, Teams.class);
        //从str转移为目标对象

        //经过思考，这一部分的删除逻辑并不能用泛型或者OBject来解决，因为涉及到对内容的基础操作，如果是泛型，就不清楚到底是什么数据结构，除非我写一个模板类，写一些模板类的方法，然后就能用泛型了。
        
        
         readed.delete(name_id);//调用了Teams的内部delete方法，把指定team删除了。
        
        
        //删除操作
        String jsonStr2 = gson.toJson(readed);
//以下为写操作-----------------------------------------------------------------------
        


        (new StringWriterI()).write(jsonStr2, filename, path);


    }}