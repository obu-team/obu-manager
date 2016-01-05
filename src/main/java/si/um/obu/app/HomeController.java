package si.um.obu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

/**
 * Created by matthahn on 15/12/15.
 */
@Controller
public class HomeController {

    @Autowired
    ServiceTemp Service;
    @RequestMapping(value = "/{obuID}",method = RequestMethod.GET)
    public ModelAndView getIndex(@PathParam("obuID") String obuID) {
        ModelAndView mw = new ModelAndView("index");
        mw.addObject("title","Hello world");
        return mw;
    }
}
