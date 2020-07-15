package com.mp.blog.common.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author tuchuan
 * @description
 * @date 2019/3/21 17:39
 */
public class JsonConvert extends AbstractGenericHttpMessageConverter<Object> {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private FastJsonConfig fastJsonConfig = new FastJsonConfig();

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public JsonConvert() {
        super(
                new MediaType("application","json",DEFAULT_CHARSET),
                new MediaType("application","xml",DEFAULT_CHARSET),
                new MediaType("text","xml",DEFAULT_CHARSET),
                new MediaType("text","plain",DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class<?> paramClass) {
        return true;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return convert(type,inputMessage);
    }

    @Override
    protected void writeInternal(Object obj, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();
        int len = JSON.writeJSONString(outnew, this.fastJsonConfig.getCharset(), obj, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializeFilters(), this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, this.fastJsonConfig.getSerializerFeatures());
        if (this.fastJsonConfig.isWriteContentLength()) {
            headers.setContentLength((long)len);
        }

        OutputStream out = outputMessage.getBody();
        outnew.writeTo(out);
        outnew.close();
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return convert(clazz,inputMessage);
    }

    private Object convert(Type clazz, HttpInputMessage inputMessage) throws IOException,HttpMessageNotReadableException{
        InputStream in = inputMessage.getBody();
        if(inputMessage.getHeaders().containsKey("Content-Type")){
            List<String> contentTypes = inputMessage.getHeaders().get("Content-Type");
            if(contentTypes != null && contentTypes.size() > 0){
                if(contentTypes.get(0).contains("json")){
                    return JSON.parseObject(in, this.fastJsonConfig.getCharset(), clazz, this.fastJsonConfig.getFeatures());
                }
            }
        }
        return inputStream2String(in);
    }

    public String inputStream2String(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return baos.toString();
    }
}
