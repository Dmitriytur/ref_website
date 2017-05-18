package ua.nure.tur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.tur.models.MessageHandler;

@Controller
public class DeviceController {


    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public String register(){
        return "device";
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public MessageHandler register(@RequestBody String deviceKey){
        return new MessageHandler();
    }
}
