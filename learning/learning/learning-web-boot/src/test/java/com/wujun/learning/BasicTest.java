package com.wujun.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.wujun.learning.config.ConfigInfo;
import com.wujun.learning.service.RemoteService;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicTest {

	@Autowired
	ConfigInfo testService;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
    private RemoteService remoteService;
	
//	@Autowired
//    private Reverser reverser;
	
    @Test
    public void test() {
    	String body = this.restTemplate.getForObject("/", String.class);
    	System.out.println(body);
    	given(this.remoteService.call("wujun")).willReturn("mock");
    	System.out.println(remoteService.call("wujun"));
    }
}
