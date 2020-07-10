package com.mp.blog.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/***
 * @description redis key过期事件监听
 * @author duchong
 * @date 2020-7-1 11:19:28
 * */
@Slf4j
@Component
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //TODO 这里需要配合redis的订阅发布功能一起使用
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("redis key 过期:key={}", key);
    }

}
