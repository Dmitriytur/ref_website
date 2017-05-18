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
import ua.nure.tur.apiutils.ApiProvider;
import ua.nure.tur.config.ConfigURL;
import ua.nure.tur.entities.User;
import ua.nure.tur.models.MessageHandler;

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
    public MessageHandler register(@RequestBody String user )  {
        return  ApiProvider.post("/register", user, String.class);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageHandler<String> login(@RequestBody String user, HttpSession session)  {
        MessageHandler<String> result = ApiProvider.post("/login", user, String.class);
        if (result.getStatus() == 200) {
            session.setAttribute("access_token", result.getData());
        }
        return result;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpSession session)  {
        String token = session.getAttribute("access_token").toString();
        MessageHandler<User> result = ApiProvider.get("/profile", User.class, token);
        model.addAttribute("user", result.getData());
        return "profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.removeAttribute("access_token");
        response.sendRedirect("/home");
    }

}
