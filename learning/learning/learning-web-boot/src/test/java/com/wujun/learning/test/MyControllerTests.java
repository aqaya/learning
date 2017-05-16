package com.wujun.learning.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wujun.learning.LearningApplication;
import com.wujun.learning.config.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningApplication.class })
public class MyControllerTests {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private Properties properties;

    @Test
    public void testExample() throws Exception {
//        given(this.userVehicleService.getVehicleDetails("sboot"))
//                .willReturn(new VehicleDetails("Honda", "Civic"));
    	MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/common/greet?name=wujun").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Hello wujun"));
    }

}