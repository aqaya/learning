package com.wujun.learning;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

import com.wujun.learning.config.ConfigInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningApplication.class })
public class MyControllerTests {

    @MockBean
    private ConfigInfo properties;

    @Autowired
    WebApplicationContext context;
    
    MockMvc mvc;
    
    @Before
    public void setup(){
    	mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void testExample() throws Exception {
        mvc.perform(get("/common/greet?name=wujun").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("Hello wujun"));
    }

}