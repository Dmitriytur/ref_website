package ua.nure.tur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.tur.apiutils.ApiProvider;
import ua.nure.tur.models.MessageHandler;
import ua.nure.tur.models.UserStateView;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {

    @RequestMapping(value = "userinfo/{targetId}", method = RequestMethod.GET)
    private String get(@PathVariable("targetId") String targetId, Model model, HttpSession session){
        String token = session.getAttribute("access_token").toString();
        model.addAttribute("targetId", Integer.parseInt(targetId));
        MessageHandler<UserStateView> result = ApiProvider.get("/userinfo/"+ targetId, UserStateView.class, token);
        model.addAttribute("state", result.getData());
        return "user_info";
    }
}
