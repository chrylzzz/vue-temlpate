package com.chryl.service;

import com.chryl.mapper.GoodsMapper;
import com.chryl.po.ChrGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chr.yl on 2020/6/10.
 *
 * @author Chr.yl
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<ChrGoods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }
}
