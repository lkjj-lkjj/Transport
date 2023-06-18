package com.example.demo5.Controller;

import com.example.demo5.AccessToken.PrividerToken;
import com.example.demo5.Privider.AccessPrivider;
import com.example.demo5.model.User;
import com.example.demo5.util.JwtUtil;
import com.example.demo5.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessTokenController {
    @Autowired
    private PrividerToken prividerToken;

    @Autowired
    private AccessPrivider accessPrivider;

    @Autowired
    private User user;

    @Autowired

    private User user1;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        prividerToken.setClient_id("8e70556e33da0ea034e85276607e26b93c5fc6dd893061c9d92e44800051cc51");
        prividerToken.setCode(code);
        prividerToken.setRedirect_uri("http://localhost:8080/callback");
        prividerToken.setState(state);
        prividerToken.setClient_Secret("b139eb710179a336a7927287ca0166ec96d617ca65f06a30fb5e9f99286aa85d");
        String Token = accessPrivider.getToken(prividerToken);
        user = accessPrivider.getUser(Token);
        user1.setLogin(user.getLogin());
        user1.setId(user.getId());
        user1.setEmail(user.getEmail());
        String JWTtoken = JwtUtil.createToken(user1);
        System.out.println("JWT token: " + JWTtoken);
        return "redirect:http://localhost:8090/createorder";
    }
}
