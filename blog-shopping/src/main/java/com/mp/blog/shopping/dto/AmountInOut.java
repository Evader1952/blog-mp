package com.mp.blog.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mapei
 * @Date: 2020/7/22 11:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountInOut {

    private  String inAmount;
    private  String outAmount;
}
