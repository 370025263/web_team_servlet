package com.major.model;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class ObjSaver {
    public ObjSaver() {
    }

    public int save(String name, String filename,String path) throws IOException {//这里没必要再返回值，可以剔除掉，应该把filename拆出来。


        File file = new File(path+filename);
        if (!file.exists()) {
            file.createNewFile();//没有就新建,然后把东西写进去。
            Teams the_first=new Teams();
            the_first.addTeam(name);
            (new StringWriterI()).write(new Gson().toJson(the_first), filename, path);//把新对象写进去
            return 1;

        }

        String readresult = (new StringReaderI()).read(filename, path);//读取过程

        Gson gson = new Gson();
        Teams the_teams = (Teams)gson.fromJson(readresult, Teams.class);//从json文件得到对象。



        //------------------------------------------以上为读取

       int re= the_teams.addTeam(name);//调用Teams的add方法，把内容添加进去。


     //------------------------------------------以下为保存
        String saving_data2 = gson.toJson(the_teams);//得到要保存的字符串

        (new StringWriterI()).write(saving_data2, filename, path);//调用写好的write方法

        return re;
    }
}
