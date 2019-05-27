package com.learning.poi;

import com.csvreader.CsvReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CndirOperation {
    public static ArrayList<ArrayList<String>> CSV2Array(String path)
    {
        try {
            BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
            String line;
            String[] onerow;
            while ((line=in.readLine())!=null) {
                onerow = line.split(",");  //默认分割符为逗号，可以不使用逗号
                List<String> onerowlist = Arrays.asList(onerow);
                System.out.println(onerowlist);
                ArrayList<String> onerowaArrayList = new ArrayList<String>(onerowlist);
                alldata.add(onerowaArrayList);
            }
            in.close();
            return alldata;
        } catch (Exception e) {
            return null;
        }

    }


    @Test
    public void demo() throws IOException {
        String parth = "D://data/dianshang.csv";

        CsvReader csv = new CsvReader("D://data/dianshang.csv", ',',Charset.forName("UTF-8"));
        // 读取表头
        csv.readHeaders();//先读取表头
        String[] headers = csv.getHeaders();//获取表头的数组
        System.out.println(Arrays.toString(headers));

        // 逐条读取记录，直至读完
        while (csv.readRecord()) {
            // 读取一条记录
            String rawRecord = csv.getRawRecord();
            System.out.println(rawRecord);
        }
        csv.close();

        //CndirOperation.CSV2Array(parth);
    }

}
