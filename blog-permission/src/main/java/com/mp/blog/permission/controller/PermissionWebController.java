package com.mp.blog.permission.controller;


import com.mp.blog.common.base.controller.BaseController;
import com.mp.blog.common.model.Response;
import com.mp.blog.common.utils.DataUtils;
import com.mp.blog.permission.entity.Menu;
import com.mp.blog.permission.entity.UserRole;
import com.mp.blog.permission.service.RoleService;
import com.mp.blog.permission.service.UserRoleService;
import com.mp.blog.permission.vo.MenuList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限管理
 *
 * @author duchong
 * @date 2019-8-3 10:50:06
 */
@Api(description = "权限web接口")
@RestController
@RequestMapping(value = "/api/web/permission")
public class PermissionWebController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<MenuList>> list() {
        List<MenuList> list = userRoleService.findPermissionByUid(1);
        if (DataUtils.isEmpty(list)){
            return Response.unsigned("没有权限");
        }
        return Response.ok(list);
    }


}