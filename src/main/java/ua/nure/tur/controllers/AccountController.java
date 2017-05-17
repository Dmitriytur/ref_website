package ua.nure.tur.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.tur.config.ConfigURL;
import ua.nure.tur.entities.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
        if (message.getStatus() == 200) {
            session.setAttribute("access_token", message.getBody());
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpSession session) throws UnirestException, IOException {
        HttpResponse<String> response = Unirest.get(ConfigURL.getApiURL() + "/getProfile")
                .header("content-type", "application/json")
                .header("Authorization", session.getAttribute("access_token").toString())
                .asString();
        User user =  new ObjectMapper().readValue(response.getBody(), User.class);
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.removeAttribute("access_token");
        response.sendRedirect("/home");
    }

}
