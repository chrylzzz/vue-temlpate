package com.chryl.service;

import com.chryl.mapper.GoodsMapper;
import com.chryl.po.ChrGoods;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> goodsList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        List<ChrGoods> allGoods = goodsMapper.goodsList();
        PageInfo<ChrGoods> pageInfo = new PageInfo<>(allGoods);
        Map<String, Object> res = new HashMap<>();
        res.put("count", pageInfo.getTotal());
        res.put("data", allGoods);
        return res;
    }
}
