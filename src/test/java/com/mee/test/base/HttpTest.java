package com.mee.test.base;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.junit.jupiter.api.Test;

/**
 * http test
 *
 * @author shadow
 * @version v1.0
 * @className HttpTest
 * @date 2023/5/14 9:49 AM
 */
public class HttpTest {

    @Test
    public void test01(){
        String str = "https://www.emptyun.com/sub.php?YmQ+YnQnbnpmPmd2b296e3FkQWhuYmptL2RwbiduenE+V0VaNFdWaUlPVVZ5UFVsMg==";
        HttpResponse execute = HttpRequest.post(str).execute();
        System.out.println(execute.headers());
        System.out.println(execute.body());
    }
}
