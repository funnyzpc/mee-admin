package com.mee.test.base;

import com.mee.common.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.quartz.impl.QrtzExecute;

/**
 * JacksonUtilTest
 *
 * @author shaoow
 * @version 1.0
 * @className JacksonUtilTest
 * @date 2024/10/31 15:46
 */
public class JacksonUtilTest {

    @Test
    public void test01(){
        String json = "{\"pid\":\"202410311540071000\",\"jobType\":\"CRON\",\"state\":\"EXECUTING\",\"cron\":\"*/2 * * * * *\",\"zoneId\":\"\",\"repeatCount\":null,\"repeatInterval\":null,\"_start_time\":\"\",\"_end_time\":\"\",\"startTime\":-1,\"endTime\":-1}";
        QrtzExecute qrtzExecute = JacksonUtil.toObject(json, QrtzExecute.class);
        System.out.println(qrtzExecute);
    }


}
