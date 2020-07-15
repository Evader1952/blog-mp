package com.mp.blog.permission.fileter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lvlu
 * @date 2019-03-06 17:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {

    private String userId;

    private String username;

    private String password;

    private String salt;

    private String roleTypes;

    private Integer state;
    private Integer type;

}
