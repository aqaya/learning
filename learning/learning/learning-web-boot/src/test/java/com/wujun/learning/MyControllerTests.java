package com.wujun.learning;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LearningApplication.class })
public class MyControllerTests {

//    @Autowired
//    WebApplicationContext context;
//
//    MockMvc mvc;
//
//    @Before
//    public void setup(){
//    	mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testExample() throws Exception {
//        mvc.perform(get("/common/greet?name=wujun").accept(MediaType.TEXT_PLAIN))
//                .andExpect(status().isOk()).andExpect(content().string("Hello wujun"));
//    }

}