package com.learning.niuke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Administrator on 2019/2/15.
 */
public class Pw {

    public static void main(String[] args) {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = bufferedReader.readLine();
            System.out.println(str);
        } catch (IOException e) {
        }

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=0;i<n;i++) {
            System.out.println(scanner.nextLine());
        }*/
        System.out.println(System.currentTimeMillis());
        Date date = new Date(1552340512);
        System.out.println(TimeUtil.formatDate(1552382052));
    }
}
