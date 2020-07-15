package com.mp.blog.permission.service;

import com.mp.blog.permission.fileter.LoginUser;
import com.mp.blog.permission.fileter.UsernamePasswordToken;

import lombok.Data;

/**
 * @author lvlu
 * @date 2019-03-06 16:13
 **/
public abstract class UserPermissionResolver {


    public abstract LoginUser getLoginUser(UsernamePasswordToken token);
    public abstract LoginUser getLoginUser(String userId);

    public LoginUser doAuthInfo(UsernamePasswordToken token){
        LoginUser loginUser = getLoginUser(token);
        if (loginUser!=null&&loginUser.getState().equals(0)){
            throw new AuthenticationException("账号已被冻结，请联系客服解冻");
        }
        if(loginUser != null && comparePassWord(token,loginUser)){
            return loginUser;
        }else{
            throw new AuthenticationException("用户名密码错误");
        }
    }

    public LoginUser doAuthInfo(String userId){
        return getLoginUser(userId);
    }


    protected Boolean comparePassWord(UsernamePasswordToken token, LoginUser loginUser){
        return token.getPassword().equals(loginUser.getPassword());
    }

    @Data
    public static class AuthenticationException extends RuntimeException{
        private Integer code;

        public AuthenticationException() {
        }

        public AuthenticationException(String message) {
            super(message);
        }

        public AuthenticationException(Integer code,String message) {
            super(message);
            this.code = code;
        }

        public AuthenticationException(Throwable cause) {
            super(cause);
        }

        public AuthenticationException(String message, Throwable cause) {
            super(message, cause);
        }

        public AuthenticationException(Integer code,String message, Throwable cause) {
            super(message, cause);
            this.code = code;
        }
    }

    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException() {
        }

        public AuthorizationException(String message) {
            super(message);
        }

        public AuthorizationException(Throwable cause) {
            super(cause);
        }

        public AuthorizationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
