package si.um.obu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.um.obu.app.model.Message;
import si.um.obu.app.model.Token;
import si.um.obu.app.service.OBUService;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class MainController {

    private Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    OBUService obuService;

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
        if(obuService.isOBUIdValid(token)) {
            return "redirect:/" + token.getValue();
        }

        //If token is not valid
        model.addAttribute("token", token);
        model.addAttribute("msg", new Message(Message.MessageType.ERROR, "OBU with given token does not exist!"));

        return "token-view";
    }

    @RequestMapping(value = "/{obuId}", method = RequestMethod.GET)
    public String homeView(@PathVariable("obuId") String OBUId, Model model) {
        model.addAttribute("location", obuService.getOBULocation(OBUId));
        model.addAttribute("params", obuService.getCarParamterValues(OBUId));
        model.addAttribute("driveHistory", obuService.getCarDriveHistory(OBUId));
        return "home-view";
    }

    @RequestMapping(value = "/{obuId}/driveHistory/{trackId}", method = RequestMethod.GET)
    public String routeView(@PathVariable("obuId") String OBUId, @PathVariable("trackId") String trackId, Model model) {
        model.addAttribute("trackId", trackId);
        model.addAttribute("obuId", OBUId);
        return "route-view";
    }

}