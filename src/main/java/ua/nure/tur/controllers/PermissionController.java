package ua.nure.tur.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.tur.apiutils.ApiProvider;
import ua.nure.tur.entities.Permission;
import ua.nure.tur.models.MessageHandler;
import ua.nure.tur.models.PermissionView;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpSession;


@Controller
public class PermissionController {

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public String permissions(Model model, HttpSession session){
        String token = session.getAttribute("access_token").toString();
        MessageHandler<PermissionView[]> result = ApiProvider.get("/permissions/to", PermissionView[].class, token);
        model.addAttribute("perms", result.getData());
        return "permissions";
    }


    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setup(){
        return "permissions";
    }

    @RequestMapping(value = "/setup", method = RequestMethod.POST)
    public MessageHandler add(@RequestBody Permission permission){
        return new MessageHandler();
    }





}
