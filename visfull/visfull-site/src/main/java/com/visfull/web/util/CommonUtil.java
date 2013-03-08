package com.visfull.web.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static BufferedImage getFasterDownScaledInstance(BufferedImage img,

    int targetWidth,

    int targetHeight,

    Object hint,

    boolean progressive) {

        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB

        : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;

        BufferedImage scratchImage = null;

        Graphics2D g2d = null;

        int w = 0, h = 0;

        int prevW = ret.getWidth();

        int prevH = ret.getHeight();

        if (progressive) {

            // Use multistep technique: start with original size,

            // then scale down in multiple passes with drawImage()

            // until the target size is reached

            w = img.getWidth();

            h = img.getHeight();

        } else {

            // Use one-step technique: scale directly from original

            // size to target size with a single drawImage() call

            w = targetWidth;

            h = targetHeight;

        }

        do {

            if (targetWidth < img.getWidth() && progressive && w > targetWidth) {

                // 如果是缩小，宽缩小为原来的一半

                w >>>= 1;

                w = (w < targetWidth) ? targetWidth : w;

            } else {

                w = targetWidth;

            }

            if (targetHeight < img.getHeight() && progressive && h > targetHeight) {

                // 如果是缩小，高缩小为原来的一半

                h >>>= 1;

                h = (h < targetHeight) ? targetHeight : h;

            } else {

                h = targetHeight;

            }

            if (scratchImage == null) {

                // Use a single scratch buffer for all iterations

                // and then copy to the final, correctly sized image before

                // returning

                scratchImage = new BufferedImage(w, h, type);

                g2d = scratchImage.createGraphics();

            } else if (type == BufferedImage.TYPE_INT_ARGB && scratchImage != null && g2d != null) {

                // 透明图片不能使用单缓存

                g2d.dispose();

                scratchImage = new BufferedImage(w, h, type);

                g2d = scratchImage.createGraphics();

            }

            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

            g2d.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

            prevW = w;

            prevH = h;

            ret = scratchImage;

        } while (w != targetWidth || h != targetHeight);

        if (g2d != null) {

            g2d.dispose();

        }

        // If we used a target size, the results into it

        if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {

            scratchImage = new BufferedImage(targetWidth, targetHeight, type);

            g2d = scratchImage.createGraphics();

            g2d.drawImage(ret, 0, 0, null);

            g2d.dispose();

            ret = scratchImage;

        }

        return ret;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
//        BufferedImage bufferedImage = ImageIO.read(new File("C:/1.JPG"));
//        
//        BufferedImage resultBufferedImage = getFasterDownScaledInstance(bufferedImage,1024,768,null,true);
//        
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/3.jpg"));
//        
//        JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
//        jpegImageEncoder.encode(resultBufferedImage);
//        
//        fileOutputStream.close();
        
//         String  baseUUID = UUID.randomUUID().toString();
//         
//         System.out.println(baseUUID);
//         
//         String first = baseUUID.substring(0,8);
//         String second = baseUUID.substring(9,17);
//         String third = baseUUID.substring(18,26);
//         String fourth = baseUUID.substring(27,35);
//         
//         System.out.println(first);
//         System.out.println(second);
//         System.out.println(third);
//         System.out.println(fourth);
//         
//         byte[] binArray = first.getBytes();
//         
//         byte[] binArray2 = second.getBytes();
//         
//         for (int i = 0; i < binArray.length; i++) {
//        }
    	
    }
    
    public static int toInt( byte[] bytes ) {
        int result = 0;
        for (int i=0; i<4; i++) {
          result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
      }
    
}
