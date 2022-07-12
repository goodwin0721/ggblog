package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 存放数据类型为T的页
 * @author goodwin
 */
@Data
@AllArgsConstructor
public class Page<T> {

    /**
     * 默认大小
     */
    public static final int DEFAULT_SIZE = 5;

    /**
     *  第几页
     */
    private int index;

    /**
     * 总页数 = ceil( 总记录数 / size )
     */
    private int pageCount;

    /**
     * 页的大小
     */
    private int size;

    /**
     * 存放类型为T的数据
     *
     * 页记录起始位置 = index * size + 1
     */
    private List<T> list;

    /**
     * 设置页的默认大小
     */
    public Page(){
        this.size = Page.DEFAULT_SIZE;
    }
}
