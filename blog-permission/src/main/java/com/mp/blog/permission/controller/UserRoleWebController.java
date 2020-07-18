package com.mp.blog.permission.controller;


import com.mp.blog.common.common.Response;
import com.mp.blog.common.exception.BizException;
import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.common.utils.PassWordUtil;
import com.mp.blog.common.utils.RequestContext;
import com.mp.blog.common.utils.StringUtils;
import com.mp.blog.permission.entity.Role;
import com.mp.blog.permission.entity.User;
import com.mp.blog.permission.entity.UserRole;
import com.mp.blog.permission.service.RoleService;
import com.mp.blog.permission.service.UserRoleService;
import com.mp.blog.permission.service.UserService;
import com.mp.blog.permission.vo.RoleSelect;
import com.mp.blog.permission.vo.UserDetail;
import com.mp.blog.permission.vo.UserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(description = "用户角色web接口")
@Slf4j
@RestController
@RequestMapping(value = "/api/web/userRole")
public class UserRoleWebController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;


    @ApiOperation(value = "新增管理账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Response addUser(User user, Long[] roleTypes, String code) {
        User users = userService.findByUsername(user.getUsername());
        if (users != null) {
            log.error("用户登录名重复:{}", user.getUsername());
            return Response.fail("用户登录名重复");
        }
        if (DataUtil.isEmpty(user.getUsername())) {
            return Response.fail("登陆账号名不能为空");
        }
        if (DataUtil.isEmpty(user.getPwd())) {
            return Response.fail("密码不能为空");
        }
        if (DataUtil.isEmpty(user.getType())) {
            return Response.fail("请选择数据角色");
        }
        if (DataUtil.isEmpty(code) && !User.Type.ADMIN.getCode().equals(user.getType())) {
            return Response.fail("请填写数据编码");
        }
        if (DataUtil.isEmpty(roleTypes)) {
            return Response.fail("请选择角色");
        }

        String salt = PassWordUtil.generateSalt();
        user.setPassword((PassWordUtil.generatePasswordSha1WithSalt(user.getPwd(), salt)));
        user.setCreateTime(new Date());
        user.setSalt(salt);
        user.setState(User.State.OPEN.getCode());
        try {
            User insert = userService.insert(user);
            //添加到userRole
            List<UserRole> userRoles = new ArrayList<>();
            for (int i = 0; i < roleTypes.length; i++) {
                UserRole userRole =  UserRole.builder().userId(insert.getId()).roleId(roleTypes[i]).build();
                userRoles.add(userRole);
            }
            userRoleService.insertBatch(userRoles);
        } catch (Exception e) {
            return Response.fail("新增管理账号失败");
        }
        return Response.ok("新增成功");
    }


    @ApiOperation(value = "角色详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),

            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "form"),
    })
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public Response<UserDetails> detail(Long id) {
        User user = userService.queryById(id);
        if (DataUtil.isEmpty(user)) {
            return Response.fail("用户详情异常");
        }
        UserDetails roleDetail = getUserDetails(user);
        //所有的角色
        List<RoleSelect> all = roleService.findAll();
        UserRole userRole =  UserRole.builder().userId(user.getId()).build();
        //用户拥有的角色
        List<UserRole> userRoles = userRoleService.queryList(userRole);
        //查出用户对应的角色
        for (RoleSelect roleSelect : all) {
            for (UserRole ur : userRoles) {
                Role query=Role.builder().id(Long.valueOf(ur.getRoleId())).build();
                Role byType = roleService.queryOne(query);
                if (byType.getId().equals(roleSelect.getType())) {
                    roleSelect.setSelect(1);
                }
            }
        }
        roleDetail.setRoleSelects(all);
        return Response.ok(roleDetail);

    }

    private UserDetails getUserDetails(User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setRemark(user.getRemark());
        userDetails.setUid(String.valueOf(user.getId()));
        userDetails.setPwd(user.getPwd());
        return userDetails;
    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "updateDetail", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> updateDetail(UserDetail userDetail) {
        if (DataUtil.isEmpty(userDetail) || DataUtil.isEmpty(userDetail.getId())) {
            return Response.fail("修改角色信息异常");
        }
        try {
            User user = setUserDetail(userDetail);
            String newPassword = user.getPwd();
            String salt = PassWordUtil.generateSalt();
            String newPass = PassWordUtil.generatePasswordSha1WithSalt(newPassword, salt);
            if (DataUtil.isNotEmpty(newPassword)) {
                user.setSalt(salt);
                user.setPassword(newPass);
            }
            //修改用户信息
            userService.updateById(user);
            //删除用户的所有角色
            userRoleService.deleteByUid(user.getId());
            //添加用户的选中的角色
            List<UserRole> userRoles = new ArrayList<>();
            for (int i = 0; i < userDetail.getRoleTypes().length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(userDetail.getRoleTypes()[i]);
                userRoles.add(userRole);
            }
            userRoleService.insertBatch(userRoles);
        } catch (BizException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("修改失败");
        }
        return Response.ok("修改成功");
    }

    @ApiOperation(value = "查看密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "/queryPwd", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> queryPwd(String pwd, String uid) {
        if (DataUtil.isEmpty(pwd)) {
            return Response.fail("密码不能为空");
        }
        RequestContext.RequestUser currentUser = RequestContext.getCurrentUser();
        User user = userService.queryById(currentUser.getId());
        User rowUser = userService.queryById(Long.valueOf(uid));
        String salt = user.getSalt();
        String password = PassWordUtil.generatePasswordSha1WithSalt(pwd, salt);
        if (user.getPassword().equals(password) && DataUtil.isNotEmpty(rowUser.getPwd())) {
            return Response.ok(rowUser.getPwd());
        }
        return Response.fail("密码错误");
    }

    private User setUserDetail(UserDetail userDetail) {
        User user = new User();
        user.setId(userDetail.getId());
        user.setRemark(userDetail.getRemark());
        user.setPwd(userDetail.getPwd());
        user.setUsername(userDetail.getUsername());
        return user;
    }
}
