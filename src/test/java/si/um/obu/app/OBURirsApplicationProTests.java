package si.um.obu.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import si.um.obu.app.model.Token;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ObuRirsApplication.class)
@WebAppConfiguration
@Profile({"pro"})
public class OBURirsApplicationProTests {

    /*
    final String BASE_URL = "http://obu-app.grega.xyz/";
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new ObuRirsApplication()).build();
    }

    @Test
    public void testGetTokenView() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html"))
                .andExpect(view().name("token-view"));
    }

    @Test
    public void testPostToken() throws Exception {
        Token token = new Token("5670a86dd4c6c730edfaf001");
        this.mockMvc.perform(post("/", token))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/" + token.getValue()));
    }
    */
}
