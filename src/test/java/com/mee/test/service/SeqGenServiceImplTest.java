package com.mee.test.service;

import com.mee.common.service.SeqGenServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

/**
 * SeqGenServiceImplTest
 *
 * @author shaoow
 * @version 1.0
 * @className SeqGenServiceImplTest
 * @date 2024/4/10 15:38
 */
@SpringBootTest
public class SeqGenServiceImplTest {

    @Autowired
    private SeqGenServiceImpl seqGenService;


    @Test
    public void test01(){
        IntStream.range(0,100000).forEach(i->{
            String yy = seqGenService.genPrimaryKey("yy");
            String dd = seqGenService.genPrimaryKey("dd");
            int i1 = Integer.parseInt(dd.substring(14));
            if( i1<1002 || i1>9996 ){
                System.out.println("yy="+yy+",dd="+dd);
            }
        });

    }

    @Test
    public void test02(){
//        Runtime.getRuntime().
    }
}
