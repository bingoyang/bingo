package com.visfull.utils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
    private static final Logger LOGGER               = LoggerFactory.getLogger(JsonUtils.class);
    public static final String  EMPTY                = "";
    public static final String  EMPTY_JSON           = "{}";
    public static final String  EMPTY_JSON_ARRAY     = "[]";
    public static final String  DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private JsonUtils() {

    }

    /**
     * 使用默认date类型，并且把null也转换
     * 
     * @param target
     * @return
     */
    public static final String toJson(Object target) {
        return toJson(target, null, null, true, null);
    }

    public static final String toJson(GsonBuilder builder, Object target) {
        return toJson(builder, target, null, null, true, null);
    }

    public static final String toJson(Object target, String datePattern) {
        return toJson(target, null, datePattern, true, null);
    }

    public static final String toJson(Object target, ExclusionStrategy strategy) {
        return toJson(target, null, null, true, strategy);
    }

    public static final String toJson(Object target, boolean disableHtmlEscaping) {
        return toJson(target, null, null, true, null, disableHtmlEscaping);
    }

    /**
     * 使用gson转换
     * 
     * @param target
     * @param targetType
     * @param datePattern
     * @param isSerializeNulls
     * @return
     */
    public static final String toJson(Object target, Type targetType, String datePattern, boolean isSerializeNulls,
            ExclusionStrategy strategy) {
        if (target == null)
            return EMPTY_JSON;
        GsonBuilder builder = new GsonBuilder();
        return toJson(builder, target, targetType, datePattern, isSerializeNulls, strategy);
    }

    public static final String toJson(Object target, Type targetType, String datePattern, boolean isSerializeNulls,
            ExclusionStrategy strategy, boolean disableHtmlEscaping) {
        if (target == null)
            return EMPTY_JSON;
        GsonBuilder builder = new GsonBuilder();
        return toJson(builder, target, targetType, datePattern, isSerializeNulls, strategy, disableHtmlEscaping);
    }

    public static final String toJson(GsonBuilder builder, Object target, Type targetType, String datePattern,
            boolean isSerializeNulls, ExclusionStrategy strategy) {
        return toJson(builder, target, targetType, datePattern, isSerializeNulls, strategy, false);
    }

    public static final String toJson(GsonBuilder builder, Object target, Type targetType, String datePattern,
            boolean isSerializeNulls, ExclusionStrategy strategy, boolean disableHtmlEscaping) {
    	builder.excludeFieldsWithoutExposeAnnotation();
    	if (disableHtmlEscaping) {
            builder.disableHtmlEscaping();
        }

        if (isSerializeNulls) {
            builder.serializeNulls();
        }
        if (datePattern == null || "".equals(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);

        if (strategy != null) {
            builder.addSerializationExclusionStrategy(strategy);
        }

        String result = EMPTY;
        Gson gson = builder.create();
        try {
            if (targetType != null) {
                result = gson.toJson(target, targetType);
            } else {
                result = gson.toJson(target);
            }
        } catch (Exception ex) {
            LOGGER.warn("目标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发生异常！", ex);
            if (target instanceof Collection || target instanceof Iterator || target instanceof Enumeration
                    || target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            } else
                result = EMPTY_JSON;
        }
        return result;
    }
    

    /**
     * 
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return (T)fromJson(json, classOfT, null);
    }

    /**
     * 
     * @param json
     * @param classOfT
     * @param fieldNamingStrategy
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT, FieldNamingStrategy fieldNamingStrategy) {
        return (T)fromJson(new GsonBuilder(), json, classOfT, fieldNamingStrategy,null);
    }

    /**
     * 
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T fromJson(String json,  Type typeOfT) {
        return (T)fromJson(new GsonBuilder(), json, typeOfT);
    }

    /**
     * 
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T fromJson(GsonBuilder builder, String json,  Type typeOfT) {
        return (T)fromJson(builder, json, typeOfT, null,null);
    }
    
    /**
     * 
     * @param builder
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T fromJson(GsonBuilder builder, String json, Type typeOfT, FieldNamingStrategy fieldNamingStrategy, String datePattern) {
        if(fieldNamingStrategy!=null){
            builder.setFieldNamingStrategy(fieldNamingStrategy);
        }
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return (T)gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            LOGGER.warn("目标JSON字符串 " + json + " 到对象的时候，发生异常！" + typeOfT.hashCode(), e);
        }
        return null;
    }
}
