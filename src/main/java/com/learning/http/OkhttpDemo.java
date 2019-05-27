package com.learning.http;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.util.CharsetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/22.
 */
public class OkhttpDemo {
    OkHttpClient client = new OkHttpClient();

    private String sendPostForm(String url, final Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry: params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client.newCall(request).execute().body().string();

    }

    private String sendGet(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        return  client.newCall(request).execute().body().string();
    }

    public static void main(String[] args) throws Exception {
        OkhttpDemo okHttpDemo = new OkhttpDemo();
        String url = "https://httpbin.org/post";
        Map<String, String> params = new HashMap<String, String>();
        params.put("foo", "bar中文");
        String rsp = okHttpDemo.sendPostForm(url, params);
        System.out.println("http post rsp:" + rsp);

        url = "https://httpbin.org/get";
        System.out.println("http get rsp:" + okHttpDemo.sendGet(url));
    }
}
