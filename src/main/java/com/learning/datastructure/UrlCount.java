package com.learning.datastructure;

import com.learning.util.TestBase;
import org.junit.Test;

import java.io.*;

public class UrlCount extends TestBase {

    @Test
    public void demo() throws IOException {
        try {
            File fileOut = new File("D:\\test2.txt");
            FileWriter fileWriter = new FileWriter(fileOut);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            File file = new File("D:\\test.txt");
            FileReader inputStreamReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String url;
            while ((url=bufferedReader.readLine())!=null) {
                System.out.println(url);
                bufferedWriter.write(url + 1+"\n");
            }
            bufferedWriter.close();
            bufferedReader.close();
        } finally {

        }

    }

    @Test
    public void sort() {
        int[] ns = new int[]{1,3,6,5,2,4,8,9,7};
        int indexl = 0;
        int indexr = ns.length-1;
        qsort(ns,indexl,indexr);
        print(ns);
    }

    private void qsort(int[] ns,int indexl,int indexr) {
        int flag = ns[indexl];
        int startIndex = indexl;
        int endIndex = indexr;
        if(indexr <= indexl) {
            return;
        }

        while (indexl != indexr) {
            while(indexr>indexl) {
                if(ns[indexr] < flag) {
                    ns[indexl] = ns[indexr];
                    break;
                }
                indexr--;
            }

            while(indexl<indexr) {
                if(ns[indexl] > flag) {
                    ns[indexr] = ns[indexl];
                    break;
                }
                indexl++;
            }
        }
        ns[indexl] = flag;

        //left
        qsort(ns,startIndex,indexl-1);
        //right
        qsort(ns,indexl+1,endIndex);

    }

}
