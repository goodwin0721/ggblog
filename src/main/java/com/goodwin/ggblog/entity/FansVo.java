package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FansVo extends UserVo{

    private String signature;
}
