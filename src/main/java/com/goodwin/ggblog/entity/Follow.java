package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关注关联
 * @author goodwin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    private int id;
    private int userId;
    private int followId;
}
