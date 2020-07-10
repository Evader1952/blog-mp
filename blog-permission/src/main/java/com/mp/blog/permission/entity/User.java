package com.mp.blog.permission.entity;

import com.mp.blog.common.model.Identifiable;
import lombok.Data;

import java.util.Date;

/**
 * @author duchong
 * @description 用户
 * @date 2019-8-3 10:05:37
 */
@Data
public class User implements Identifiable<Long> {

    private Long id;

    private String uid;

    private String username;

    private String password;

    private String mobile;

    private String nickName;

    private Date createTime;

    private Integer state;

    private Integer type;

    private String salt;

    private String pwd;
    /**
     * 备注
     */
    private String remark;

    public enum State {
        /**
         * 开启
         */
        OPEN(1),
        /**
         * 关闭
         */
        CLOSE(0);

        private Integer code;

        State(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }


    public enum Type {
        /**
         * 管理员
         */
        ADMIN(0),
        /**
         * 门店角色
         */
        USER(1),
        /**
         * 商户角色
         */
        MANAGE(2),
        /**
         * 市级账号
         * */
        CITY(3),
        /**
         * 省级账号
         * */
        PROVINCE(4),
        /**
         * 红包配置账号
         * */
        RED_CONFIG(5),
        /**
         * 退款账号
         * */
        REFUND(6);

        private Integer code;

        Type(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    public String getTypeDesc(){
        if (User.Type.ADMIN.getCode().equals(type)){
            return "管理员";
        }else if (User.Type.USER.getCode().equals(type)){
            return "门店角色";
        }else if (User.Type.MANAGE.getCode().equals(type)){
            return "商户角色";
        }else if (User.Type.CITY.getCode().equals(type)){
            return "市级账号";
        }else {
            return "省级账号";
        }

    }

    public String getStateDesc(){
        if (State.CLOSE.getCode().equals(state)){
            return "已冻结";
        }else {
            return "未冻结";
        }
    }
}
