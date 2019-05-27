package com.learning.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2019/4/10.
 */
public class HttpClientDemo2 {
    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void get() throws InterruptedException {
        //doget();
        for(int i=0;i<10;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doget();
                }
            }).start();
        }
        Thread.sleep(100000);
    }

    private void doget() {
        HttpGet httpGet = new HttpGet("Http://realname.test.zbjdev.com/resource/download?key=homesite%2Ftask%2Ffile%2Forigine%2F5e244d35-8fc2-43bf-a98d-b27386fc1c54&token=2832C3DF1A7B7153D613678794ECD7AF16A45B44C1F22853599F93C2485C86673E40DBE148ACADF4427C26BC15317473367A1F57134156DE91F1AEDCBA79EBF85C201FA3484663B7C36E3C4179C80CB417906EFFC48317F3C46B576973FDB6D7CB53B966BB008522E33737F44C63B4E7");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
