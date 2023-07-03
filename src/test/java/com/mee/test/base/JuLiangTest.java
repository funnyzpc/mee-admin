package com.mee.test.base;

import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mee.common.util.JacksonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JuLiangTest {

/*
测试APPID 1676174679967758
测试access_token  5fae46b926243cca43053ff784e178c293d0d9be
重置
虚拟广告主账号 298674052938878
虚拟代理商账号 4177759923349428
 */
private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void process01(){
        String url = "https://test-ad.toutiao.com/open_api/oauth2/access_token/";
        Map<String,String> header = new HashMap<String,String>(2,1){{
            put("Content-Type","application/json");
            put("X-Debug-Mode","1");
        }};
        Map<String,Object> body = new HashMap<String,Object>(4,1){{
            put("app_id","1676174679967758");
            put("secret","5fae46b926243cca43053ff784e178c293d0d9be");
            put("grant_type","auth_code");
            put("auth_code","AU"+System.currentTimeMillis());
        }};

        String result = HttpRequest.post(url)
                .body(JacksonUtil.toJsonString(body))
                .headerMap(header,false)
                .execute().body();
        System.out.println(result);

    }
    @Test
    public void test02()throws Exception{
        String url = "https://test-ad.toutiao.com/open_api/2/report/audience/province/";

        Map<String,String> header = new HashMap<String,String>(2,1){{
            put("Access-Token","5fae46b926243cca43053ff784e178c293d0d9be");
            put("X-Debug-Mode","1");
        }};
        String[] metrics = { "cost", "show", "click", "download_finish", "convert"};
        Map<String,Object > body = new HashMap<String,Object>(5,1){{
            put("advertiser_id","298674052938878");
            put("start_date","2020-07-01");
            put("end_date","2020-07-31");
            put("id_type","AUDIENCE_STAT_ID_TYPE_ADVERTISER");
            // put("ids","AUDIENCE_STAT_ID_TYPE_ADVERTISER");
            // put("metrics",metrics);
            put("metrics","[\"cost\", \"show\", \"click\", \"download_finish\", \"convert\"]");
        }};

        System.out.println(url);
        String result = HttpRequest.get(url)
                // .body(JacksonUtil.toJsonString(body))
                .form(body)
                .headerMap(header,false)
                .execute().body();
        System.out.println(result);
    }

}
