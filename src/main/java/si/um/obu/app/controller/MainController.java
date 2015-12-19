package si.um.obu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.um.obu.app.model.Message;
import si.um.obu.app.model.Token;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class MainController {

    private Logger log = Logger.getLogger(MainController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getTokenFormView(Model model) {
        model.addAttribute("token", new Token());
        return "token-view";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String tokenSubmit(@ModelAttribute @Valid Token token, BindingResult bindingResult, Model model) {
        log.info("tokenSubmit method params: token = [" + token + "], bindingResult = [" +
                bindingResult + "], model = [" + model + "]");

        if(bindingResult.hasErrors()) {
            model.addAttribute("token", token);
            model.addAttribute("msg", new Message(Message.MessageType.ERROR, "Token format is not valid!"));
            return "token-view";
        }

        // Če je token veljaven preusmeri na "home" stran od OBU-ta
        //TODO: pošlji token na register OBU naprav in pridobi OBU id.

        //If token is not valid
        model.addAttribute("token", token);
        model.addAttribute("msg", new Message(Message.MessageType.ERROR, "OBU with given token does not exist!"));

        return "redirect:/" + token.getValue();
    }

    @RequestMapping(value = "/{obuId}", method = RequestMethod.GET)
    public String homeView() {
        return "home-view";
    }

}
