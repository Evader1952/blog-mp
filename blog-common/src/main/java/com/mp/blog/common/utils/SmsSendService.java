package com.mp.blog.common.utils;

import com.mp.blog.common.exception.SMSException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lvlu
 * @date 2019-06-12 10:51
 **/
@Slf4j
@Component
public class SmsSendService {

    @Value("${sms.gms.url}")
    private String gmsurl;

    @Value("${sms.gms.orgid}")
    private String orgid;

    @Value("${sms.gms.username}")
    private String username;

    @Value("${sms.gms.password}")
    private String password;

    public static final String TYPE_LOGIN = "0";

    public static final String TYPE_UPDATE = "1";

    private static final String SMSCODE_PREFIX = "smsCodeSend:";

    private static final String SPLITER = ":";

    private static final Long EXPIRES = 16 * 60 * 1000L;

    private static final Long MIN_EXPIRES = 60 * 1000L;

    private static final String SMS_TEMPLATE_LOGIN = "【商洛交警】您正在进行登录验证，验证码为CODE";



    public boolean sendSmsCode(String mobile, String type) throws SMSException {
        String key = createKey(mobile,type);
        Long expires = RedisUtil.getExpire(key);
//        if(expires > MIN_EXPIRES){
//            throw new SMSException("短信验证码已发送过，请耐心等待");
//        }

        //TODO 方便测试暂时设置为1234
//        String code = Commons.createRandom(true,4);
        //TODO 短信发送逻辑待确认
//        Boolean flag = sendByGms(SMS_TEMPLATE_LOGIN.replace("CODE",code),mobile);
        String code = "1234";
        Boolean flag = true;
        if(flag) {
            RedisUtil.set(key, code, EXPIRES);
            return true;
        }else{
            return false;
        }
    }

    public boolean validSmsCode(String mobile,String type,String code){
        String key = createKey(mobile,type);
        Object object = RedisUtil.get(key);
        String toValid = object == null ? null : (String) object;
        if(DataUtil.isEmpty(toValid)){
            return false;
        }
        if(toValid.equals(code)){
            RedisUtil.del(key);
            return true;
        }
        return false;
    }

    private static String createKey(String mobile,String type){
        return SMSCODE_PREFIX+type+SPLITER+mobile;
    }

    private static final HttpClient SMSCLIENT = HttpClients.custom().build();
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(45000).setConnectionRequestTimeout(10000)
            .setSocketTimeout(45000).build();

    private boolean sendByGms(String content,String telphone){
        try {
            if (telphone.length() > 0 && telphone.indexOf('2') == 0) {
                telphone = "1" + telphone.substring(1);
            }
            HttpPost httppost = new HttpPost(gmsurl);
            httppost.setConfig(REQUEST_CONFIG);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", orgid+":"+username));
            nvps.add(new BasicNameValuePair("password",password));
            nvps.add(new BasicNameValuePair("from",""));
            nvps.add(new BasicNameValuePair("to",telphone));
            nvps.add(new BasicNameValuePair("content",content));
            httppost.setEntity(new UrlEncodedFormEntity(nvps, "GBK"));
            HttpResponse response = SMSCLIENT.execute(httppost);
            HttpEntity entity = response.getEntity();
            if(entity!=null) {
                String entitystr = EntityUtils.toString(entity);
                if(entitystr.startsWith("OK:")){
                    return true;
                }else{
                    log.error("短信发送失败，错误代码【{}】",entitystr.replaceAll("\r|\n", ""));
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }
}
