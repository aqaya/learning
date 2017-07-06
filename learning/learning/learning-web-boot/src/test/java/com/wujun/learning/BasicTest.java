package com.wujun.learning;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicTest {

//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@MockBean
//    private RemoteService remoteService;
//
////	@Autowired
////    private Reverser reverser;
//
//    @Test
//    public void test() {
//    	String body = this.restTemplate.getForObject("/", String.class);
//    	System.out.println(body);
//    	given(this.remoteService.call("wujun")).willReturn("mock");
//    	System.out.println(remoteService.call("wujun"));
//    }
}
