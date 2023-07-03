package com.mee.common.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author funnyzpc
 * @description 对jackson进行的封装
 */
public class JacksonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(new JavaTimeModule());

    /** 将java bean转换为json字符串**/
    public static <T> String toJsonString(T object) {
        try {
            if(null ==  object){
                return null;
            }
            if(object instanceof String){
                return (String)object;
            }
            return  OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.error("convert POJO to JSON failure", e);
            return null;
        }
    }

    /** json字符串转换为java bean**/
    public static <T> T toObject(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("convert JSON to POJO failure", e);
            return null;
        }
    }

    /**
     * json str转复合对象
     *  example : toObject(json_str,new TypeReference<List<Entity>>(){});
     * @param json
     * @param type
     * @return
     * @param <T>
     */
    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("convert JSON to POJO failure", e);
            return null;
        }
    }

}
