package ua.nure.tur.aspects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdvice {

    @ModelAttribute("authorized")
    public boolean isAuthorized() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null)
        {
            return false;
        }
        if (auth.getPrincipal() == null)
        {
            return  false;
        }
        return auth.getPrincipal().toString() != "anonymousUser";
    }

}
