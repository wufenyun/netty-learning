package com.learning.poi;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {

    private final static String SPLIT = "/";
    private final static String PREFIX = "www.zbj.com";

    public static List<UrlAndCndir> listNormalUrls(File file) {
        BufferedReader in = null;
        try {
            List<UrlAndCndir> normalUrls = new ArrayList<>();
            in =new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line;
            String[] onerow;
            while ((line=in.readLine())!=null) {
                onerow = line.split(",");  //默认分割符为逗号，可以不使用逗号
                if(onerow==null || onerow.length<3) {
                    //System.out.println("不规范url--------------：" + line);
                    continue;
                }
                String url = onerow[1];

                String[] urls =  url.split(SPLIT);
                if(urls!=null && urls.length>=3 && url.startsWith(PREFIX)) {
                    UrlAndCndir urlAndCndir = new UrlAndCndir();
                    urlAndCndir.setCndir(urls[1]);
                    urlAndCndir.setUrl(url);
                    normalUrls.add(urlAndCndir);
                    //System.out.println(url + "     " + urls[1]);
                } else {
                    //System.out.println(url + "*******************" );
                }
            }
            return normalUrls;
        } catch (IOException e) {
            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
