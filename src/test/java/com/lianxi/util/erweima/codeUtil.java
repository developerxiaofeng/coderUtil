package com.lianxi.util.erweima;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @Author: developerxiaofeng
 * @Description:
 * @Date: Created in 11:06 2018/7/24
 */
public class codeUtil {
    /**
     * 生成二维码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     * @throws Exception
     */
    public static void encode(String contents, int width, int height, String imgPath) throws Exception {
        Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, width, height, hints);

        MatrixToImageWriter.writeToStream(bitMatrix, "png",
                new FileOutputStream(imgPath));


    }

    /**
     * 解析二维码
     *
     * @param imgPath
     * @return
     * @throws Exception
     */
    public static String decode(String imgPath) throws Exception {
        BufferedImage image = null;
        Result result = null;

        image = ImageIO.read(new File(imgPath));
        if (image == null) {
            System.out.println("the decode image may be not exit.");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Map<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();


    }

    public static void main(String[] args) throws Exception {
        encode("http://39.106.23.93:8089/pages/index",1000,1000,"D:/自己博客.png");

        System.out.println(decode("D:/test2.png"));


    }
}
