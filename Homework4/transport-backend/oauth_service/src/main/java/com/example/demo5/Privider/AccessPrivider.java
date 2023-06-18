package com.example.demo5.Privider;

import com.alibaba.fastjson.JSON;
import com.example.demo5.AccessToken.PrividerToken;
import com.example.demo5.model.User;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessPrivider {
    public String getToken(PrividerToken prividerToken) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(prividerToken));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code=" + prividerToken.getCode() + "&client_id=" + prividerToken.getClient_id() + "&redirect_uri=" + prividerToken.getRedirect_uri() + "&client_secret=" + prividerToken.getClient_Secret())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();

            String str1 = string.split(":")[1];
            String str2 = str1.split("\"")[1];
            System.out.println("token:    " + str2);
            System.out.println(string);
            return str2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String Token) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + Token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            User user = JSON.parseObject(string, User.class);
            System.out.println(user.getLogin());
            System.out.println(user.getId());

            return user;
        } catch (IOException e) {

        }
        return null;
    }


}
