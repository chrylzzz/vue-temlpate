package com.chryl.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Chr.yl on 2020/6/12.
 *
 * @author Chr.yl
 */
@Data
public class PageVo implements Serializable{
    private static final long serialVersionUID = -5894574918701056372L;
    private Integer page;
    private Integer limit;

    public PageVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
}
