package si.um.obu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import si.um.obu.app.model.*;
import si.um.obu.app.service.OBUService;

import javax.validation.Valid;
import java.util.List;
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
        model.addAttribute("params", obuService.getCarParamterValues(OBUId));
        return "home-view";
    }


    @ResponseBody
    @RequestMapping(value = "/{obuId}/driveHistory", method = RequestMethod.GET)
    public List<Track> getCarDriveHistory(@PathVariable("obuId") String OBUId) {
        return obuService.getCarDriveHistory(OBUId);
    }

    @ResponseBody
    @RequestMapping(value = "/{obuId}/location", method = RequestMethod.GET)
    public GeoLocation getCarLocation(@PathVariable("obuId") String OBUId) {
        return obuService.getOBULocation(OBUId);
    }

    @ResponseBody
    @RequestMapping(value = "/{obuId}/destination", method = RequestMethod.GET)
    public GeoLocation getCarDestination(@PathVariable("obuId") String OBUId) {
        return obuService.getCarDestination(OBUId);
    }

    @ResponseBody
    @RequestMapping(value = "/{obuId}/error", method = RequestMethod.GET)
    public List<CarError> getCarErrors(@PathVariable("obuId") String OBUId) {
        return obuService.getCarErrors(OBUId);
    }
}
