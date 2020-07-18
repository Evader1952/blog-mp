package com.mp.blog.permission.controller;

import com.alibaba.fastjson.JSONObject;
import com.mp.blog.common.common.Response;
import com.mp.blog.permission.service.RoleService;
import com.mp.blog.common.exception.BizException;
import com.mp.blog.common.utils.DataUtil;
import com.mp.blog.permission.entity.*;
import com.mp.blog.permission.query.RoleQuery;
import com.mp.blog.permission.service.*;
import com.mp.blog.permission.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "角色web接口")
@Slf4j
@RestController
@RequestMapping(value = "/api/web/role")
public class RoleWebController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @Autowired
    private RoleMenuService roleMenuService;


    @ApiOperation(value = "角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @PostMapping(value = "list")
    @ResponseBody
    public Response<Page<RoleWebList>> list(RoleQuery query) {
        if (DataUtil.isEmpty(query.getPage())) {
            query.setPage(0);
        }
        if (DataUtil.isEmpty(query.getLimit())) {
            query.setLimit(10);
        }
        Pageable pageable = PageRequest.of(query.getPage(), query.getLimit());
        Page<Role> page = roleService.queryPage(query, pageable);
        List<RoleWebList> voList = new ArrayList<>();
        for (Role role : page.getContent()) {
            User user = new User();
            //user.setType(role.getId());
            Long count = userService.queryCount(user);
            RoleWebList roleWebList = new RoleWebList();
            roleWebList.setId(role.getId());
            roleWebList.setName(role.getName());
            roleWebList.setType(Math.toIntExact(role.getId()));
            roleWebList.setCount(count);
            roleWebList.setRemark(role.getRemark());
            roleWebList.setState(role.getState());
            roleWebList.setStateDesc(role.getStateDesc());
            voList.add(roleWebList);
        }
        Page<RoleWebList> voPage = new PageImpl<>(voList, pageable, page.getTotalElements());
        return Response.ok(voPage);
    }

    @ApiOperation(value = "冻结/解冻角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "/freezeRole", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> freezeRole(RoleDetail roleDetail) {
        try {
            if (DataUtil.isEmpty(roleDetail)) {
                return Response.fail("角色为空");
            }
            //冻结/解冻角色
            Role role = new Role();
            role.setState(roleDetail.getRoleState());
            role.setId(roleDetail.getId());
            //传过来role  id 和 stat=0
            roleService.updateById(role);
            //改 role_menu state为0
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleDetail.getType());
            roleMenu.setState(roleDetail.getRoleMenuState());
            //冻结/解冻角色对应权限
          //  roleMenuService.updateByType(roleMenu);
        } catch (Exception e) {
            log.error("冻结角色失败:{}", e);
            return Response.fail("冻结角色失败");
        }
        return Response.ok("冻结成功");
    }

    @ApiOperation(value = "新增角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @PostMapping(value = "addRole")
    @ResponseBody
    public Response<String> addRole(Role role, String roleMenus) {
        List<RoleMenu> roleMenusList = JSONObject.parseArray(roleMenus, RoleMenu.class);
        if (DataUtil.isEmpty(role)) {
            return Response.fail("角色不能为空");
        }
        if (DataUtil.isEmpty(roleMenusList)) {
            return Response.fail("所选权限不能为空");
        }
        try {

            role.setState(1);
            Role insert = roleService.insert(role);
            if (DataUtil.isNotEmpty(roleMenusList)) {
                for (RoleMenu roleMenu : roleMenusList) {
                    roleMenu.setRoleId(insert.getId());
                    roleMenu.setState(1);
                }
                //添加角色对应的权限
                roleMenuService.insertBatch(roleMenusList);
            }
        } catch (BizException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("添加失败");
        }
        return Response.ok("添加成功");
    }


//    @ApiOperation(value = "树列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
//    })
//    @PostMapping(value = "treeList")
//    @ResponseBody
//    public Response<List<TreeList>> treeList() {
//      //  List<TreeList> tree = roleService.findTree();
//        return Response.ok(tree);
//    }

    @ApiOperation(value = "角色详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),

            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "form"),
    })
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public Response<RoleDetails> detail(Long id) {
        if (DataUtil.isEmpty(id)) {
            return Response.fail("查看角色详情异常");
        }
//        Role role = roleService.queryById(id);
//        if (DataUtil.isEmpty(role)) {
//            return Response.fail("角色详情异常");
//        }
//        RoleDetails roleDetail = getRoleDetail(role);
//
//        List<TreeList> tree = roleService.findTree();//所有数据
//        RoleMenu roleMenu = new RoleMenu();
//        roleMenu.setType(role.getType());
//        List<RoleMenu> list = roleMenuService.queryList(roleMenu);
//        for (TreeList treeList : tree) {
//            for (RoleMenu rm : list) {
//                if (treeList.getChildren() == null) {
//                    continue;
//                }
//                for (TreeList menuList : treeList.getChildren()) {
//                    if (menuList.getCode().equals(rm.getMenuCode())) {
//                        menuList.setChecked(true);
//                    }
//                    if (menuList.getChildren() == null) {
//                        continue;
//                    }
//                    for (TreeList btnList : menuList.getChildren()) {
//                        if (btnList.getCode().equals(rm.getMenuCode())) {
//                            btnList.setChecked(true);
//                        }
//                    }
//
//                }
//            }
//        }
//        roleDetail.setTreeList(tree);
      //  return Response.ok(roleDetail);
        return Response.ok(null);
    }

    @ApiOperation(value = "修改角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @RequestMapping(value = "updateDetail", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> updateDetail(RoleDetails detail, String roleMenus) {
        List<RoleMenu> roleMenusList = new ArrayList<>();
        if (!roleMenus.equals("") || DataUtil.isNotEmpty(roleMenus)) {
            roleMenusList = JSONObject.parseArray(roleMenus, RoleMenu.class);
        }
        if (DataUtil.isEmpty(detail) || DataUtil.isEmpty(detail.getId()) || DataUtil.isEmpty(detail.getType())) {
            return Response.fail("修改角色信息异常");
        }
        try {
            Role role = setRoleDetail(detail);
            //修改角色信息
            roleService.updateById(role);
            //删除角色的所有权限信息
            if (DataUtil.isNotEmpty(roleMenusList)) {
             //   roleMenuService.deleteByType(role.getType());
                //再添加角色的权限信息
                for (RoleMenu roleMenu : roleMenusList) {
                 //   roleMenu.setType(role.getType());
                    roleMenu.setState(1);
                }
                roleMenuService.insertBatch(roleMenusList);
            }
        } catch (BizException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("修改失败");
        }
        return Response.ok("修改成功");
    }

    private Role setRoleDetail(RoleDetails detail) {
        Role role = new Role();
        role.setId(detail.getId());
        role.setName(detail.getName());
        role.setRemark(detail.getRemark());
        return role;
    }

    @ApiOperation(value = "角色类型下拉框")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "加密参数", required = true, dataType = "String", paramType = "header"),
    })
    @PostMapping(value = "roleSelectList")
    @ResponseBody
    public Response<List<RoleSelect>> roleSelectList() {
        List<RoleSelect> roleSelects = roleService.findAll();
        return Response.ok(roleSelects);

    }


    /**
     * 详情
     *
     * @param role
     * @return
     */
    private RoleDetails getRoleDetail(Role role) {
        RoleDetails roleDetail = new RoleDetails();
        roleDetail.setId(role.getId());
        roleDetail.setName(role.getName());
        roleDetail.setRemark(role.getRemark());
        return roleDetail;
    }


}
