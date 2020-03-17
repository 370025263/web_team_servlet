package com.major.model;

import org.springframework.stereotype.Component;

import java.io.*;
@Component

public class StringReaderI {
    public StringReaderI() {
    }


    public static String read(String filename ,String path) throws IOException {
        filename = path+ filename;//希望这个能够把"/www/server/tomcat/bin/data/" 字符串拿出来单独作为一个path，然后通过value注解注入。


        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
        StringBuffer strbuffer = new StringBuffer();
        BufferedReader in = new BufferedReader(inputStreamReader);

        String str;
        while((str = in.readLine()) != null) {
            strbuffer.append(str);
        }

        String readresult = strbuffer.toString();
        in.close();
        return readresult;

    }
}
