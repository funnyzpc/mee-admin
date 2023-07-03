package com.mee.test.base;

import com.mee.common.util.JacksonUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import org.junit.jupiter.api.Test;

/**
 * ResultBuild
 *
 * @author shadow
 * @version v1.0
 * @className ResultBuildTest
 * @date 2023/6/21 9:29 PM
 */
public class ResultBuildTest {

    @Test
    public void test01(){
        MeeResult<Double> rst = ResultBuild.build(1233.32);
        System.out.println(JacksonUtil.toJsonString(rst));
    }
}
