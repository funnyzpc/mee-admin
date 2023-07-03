package com.mee.test;

import com.mee.common.util.JacksonUtil;
import com.mee.sys.entity.SysDict;

import java.math.BigDecimal;
import java.util.*;

public class Test01 {


   //@Test
    public void process01(){
        System.out.println(0%2);
    }

    //@Test
    public void process02(){
        String[] strArr = new String[]{"AA","BB"};
        Arrays.stream(strArr).forEach(i->{
            System.out.println(i);
        });
    }

    //@Test
    public void process03()throws Exception{
        SysDict d = new SysDict(){{
           setId(UUID.randomUUID().toString());
           setName("XXX");
        }};
        String json = JacksonUtil.toJsonString(d);
        System.out.println(json);
    }


    // @Test
    public void process04(){
        HashMap<String,Object>  mp1 = new HashMap<String,Object>(){{
            put("bool",Boolean.TRUE);
            put("int",123);
            put("str","hello");
            put("arr",new String[]{"aa","bb"});
            put("bg",new BigDecimal("22.222"));
            put("date",new Date());
        }};
        HashMap<String,Object>  mp2 = new HashMap<String,Object>(){{
            put("double",3.335D);
            put("long",123456789L);
            put("byte",new byte[]{101,102,'c',105});
            put("bool",Boolean.TRUE);
            put("int",123);
            put("lst",new ArrayList<String>());
        }};

        List<Map<String,Object>> lst = new ArrayList<Map<String,Object>>(1){{
            add(mp1);
            add(mp2);
        }};

        String json = JacksonUtil.toJsonString(lst);
        System.out.println(json);
    }
}
