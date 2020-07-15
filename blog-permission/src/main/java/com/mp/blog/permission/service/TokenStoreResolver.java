package com.mp.blog.permission.service;


import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.common.utils.RedisUtil;
import com.mp.blog.permission.fileter.JwtUtil;

import java.util.Date;

/**
 * @author lvlu
 * @date 2019-03-06 11:57
 **/
public interface TokenStoreResolver {

    String KEYPREFIX = "JwtToken:";
    Long delaysAllowed = 100L;

    /**
     * token存储
     *
     * @param token
     * @param userId
     * @param expireTime
     */
    default void addOrUpdateToken(String token, String userId, Date expireTime) {
        Long ttl = expireTime.getTime() - System.currentTimeMillis();
        if (ttl > delaysAllowed) {
            RedisUtil.set(KEYPREFIX + token, userId, ttl);
        } else {
            throw new JwtUtil.TokenValidationException("token已过期");
        }
    }

    /**
     * 校验token是否还能使用，包括校验token的有效时间
     *
     * @param token
     * @return
     */
    default Boolean validateToken(String token){
        Object object = RedisUtil.get(KEYPREFIX+token);
        String userId = object == null ? null : (String) object;
        if(DataUtil.isNotEmpty(userId)){
            return true;
        }else{
            throw new JwtUtil.TokenValidationException("token已过期");
        }
    }

    /**
     * 删除token,使token不能再使用
     *
     * @param token
     */
    default void deleteTokenByToken(String token){
        RedisUtil.del(KEYPREFIX+token);
    }
}
