package com.lianxi.util.duanxin;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

/**
 * @Author: developerfengrui
 * @Description: 短信服务
 * @Date: Created in 11:14 2018/7/18
 */
public class SendMessageUtil {

    public static void main(String[] args) {
        String[] phone = {""};  //手机号
        String[] params = {"wawaww", "3"};  //验证码+失效时间
        send(1400114511, "",
                phone, 15820, "个人公众号", params);
    }

    /**
     * @param appid        例 1400114511
     * @param appkey       例  5c70ba3ca084c5fc92950eb7851598a7
     * @param phoneNumbers 电话号码数组  String [] phone ={"18435138713"};
     * @param templateId   例   158220
     * @param smsSign      例 haha
     * @param params       例     {"验证码","分钟"};
     * @Description: 【Java程序员技术分享】[haha]wawa为您的登录验证码，请于100分钟内填写。如非本人操作，请忽略本短信。
     * @Date: 14:37  2018/7/19
     * @Param:
     */
    public static String send(int appid, String appkey,
                              String[] phoneNumbers, int templateId, String smsSign, String[] params) {

        SmsSingleSenderResult result = null;
        try {

            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return result.toString();
    }
}
