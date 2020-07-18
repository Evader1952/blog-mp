package com.mp.blog.permission.controller;


import com.mp.blog.common.common.Response;
import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.common.utils.RequestContext;
import com.mp.blog.permission.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "用户web接口")
@RequestMapping(value = "/api/web/user")
@RestController
@Component
public class UserWebController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login")
    @ResponseBody
    public Response login(String username, String password) {
        return Response.ok("登录成功");
    }

    @ApiOperation(value = "修改登录密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "Integer", paramType = "form"),

            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Response changePassword(String oldPassword, String newPassword) {
        if (DataUtil.isEmpty(oldPassword)) {
            return Response.fail("缺少原密码");
        }
        if (DataUtil.isEmpty(newPassword)) {
            return Response.fail("缺少新密码");
        }
        RequestContext.RequestUser user = RequestContext.getCurrentUser();
        String result = userService.changePassword(Long.valueOf(user.getId()), user.getSalt(), user.getPassword(), oldPassword, newPassword);
        if (result != null) {
            return Response.fail(result);
        }
        return Response.ok("修改成功，请重新登录");
    }

}
