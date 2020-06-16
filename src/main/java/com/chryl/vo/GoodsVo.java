package com.chryl.vo;

import com.chryl.po.ChrGoods;
import lombok.Data;

import java.util.List;

/**
 * Created by Chr.yl on 2020/6/16.
 *
 * @author Chr.yl
 */
@Data
public class GoodsVo extends ChrGoods {

    List<String> goodsColors;

    private Integer page;
    private Integer limit;
}
