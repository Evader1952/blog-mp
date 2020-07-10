package com.mp.blog.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duchong
 * @description
 * @date 2019-11-23 14:14:07
 */
@Data
@NoArgsConstructor
@ApiModel(description = "接口返回数据", value = "BaseResult")
public class BaseResult {

    @ApiModelProperty(value = "具体业务描述")
    private String error_code;

    @ApiModelProperty(value = "具体业务错误码")
    private String error_msg;

    @ApiModelProperty(value = "操作返回码[\"10500\", \"系统异常\"],[\"40015\", \"登陆过期\"],[\"30015\", \"版本过期\"],[\"40500\", \"操作失败\"],[\"20000\", \"操作成功\"]")
    private String code;

    @ApiModelProperty(value = "错误描述")
    private String msg;
}
