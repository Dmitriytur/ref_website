package ua.nure.tur.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.tur.config.ConfigURL;

import javax.servlet.http.HttpSession;


@Controller
public class AccountController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody String user ) throws UnirestException {

        HttpResponse<String> message = Unirest.post(ConfigURL.getApiURL() + "/register")
                .header("content-type", "application/json")
                .body(user)
                .asString();
        return message.getBody();

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody String user, HttpSession session) throws UnirestException {
        HttpResponse<String> message = Unirest.post(ConfigURL.getApiURL() + "/login")
                .header("content-type", "application/json")
                .body(user)
                .asString();
        session.setAttribute("access_token", message.getBody());
    }


}
