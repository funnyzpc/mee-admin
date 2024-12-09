package com.mee.test.base;

import com.mee.common.util.JacksonUtil;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;

/**
 * DateUtilTest
 *
 * @author shaoow
 * @version 1.0
 * @className DateUtilTest
 * @date 2024/11/13 11:07
 */
public class DateUtilTest {


    @Test
    public void test01(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        ArrayList<String> strings = new ArrayList<>(availableZoneIds);
        Collections.sort(strings);
        System.out.println(JacksonUtil.toJsonString(availableZoneIds));
        System.out.println(JacksonUtil.toJsonString(strings));
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault.getID());
    }
}
