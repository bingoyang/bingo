package com.visfull.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 */
public final class RenderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenderUtils.class);
    
    private RenderUtils() {
        
    }
    /**
     * 
     * @param text
     * @param contentType
     * @param response
     * @return
     */
    public static String render(String text, String contentType, HttpServletResponse response) {
        try {
            response.setContentType(contentType);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().write(text);
            response.flushBuffer();
        } catch (IOException e) {
            LOGGER.warn("render出错contentType: " + contentType  
                    + ",body:" + text, e);  
        }
        return null;
    }

    /**
     * 直接输出字符串
     */
    public static String renderText(String text, HttpServletResponse response) {
        return render(text, "text/plain;charset=UTF-8", response);
    }

    /**
     * 直接输出HTML.
     */
    public static String renderHtml(String html, HttpServletResponse response) {
        return render(html, "text/html;charset=UTF-8", response);
    }

    /**
     * 直接输出XML.
     */
    public static String renderXML(String xml, HttpServletResponse response) {
        return render(xml, "text/xml;charset=UTF-8", response);
    }

    /**
     * 输出json
     * @param json
     * @param response
     * @return
     */
    public static String renderJson(String json, HttpServletResponse response) {
        return render(json, "application/json;charset=UTF-8", response);
    }

}
