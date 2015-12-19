package si.um.obu.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import si.um.obu.app.model.Token;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ObuRirsApplication.class)
@WebAppConfiguration
@Profile({"dev", "test"})
public class ObuRirsApplicationTests {

    final String BASE_URL = "http://127.0.0.1:8080/";

    /*@Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetTokenView() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html"))
                .andExpect(view().name("token-view"));
    }

    @Test
    public void testPostToken() throws Exception {
        Token token = new Token("5670a86dd4c6c730edfaf001");
        this.mockMvc.perform(post(BASE_URL + "/", token))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/" + token.getValue()));
    }*/

}
