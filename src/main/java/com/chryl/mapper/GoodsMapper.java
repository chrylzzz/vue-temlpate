package com.chryl.mapper;

import com.chryl.po.ChrGoods;

import java.util.List;

/**
 * Created by Chr.yl on 2020/6/10.
 *
 * @author Chr.yl
 */
public interface GoodsMapper {

    List<ChrGoods> getAllGoods();

    List<ChrGoods> goodsList();

}
