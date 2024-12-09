package com.mee.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JsonDateSerializer
 *
 * @author shaoow
 * @version 1.0
 * @className JsonDateSerializer
 * @date 2024/10/14 9:35
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    private final String fmt="yyyy-MM-dd HH:mm:ss";
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(null==date){
            return;// 直接结束
        }
        final String value = new SimpleDateFormat(fmt).format(date);
        jsonGenerator.writeString(value);
    }

}
