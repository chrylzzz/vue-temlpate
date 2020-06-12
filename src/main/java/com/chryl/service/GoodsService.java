package com.chryl.service;

import com.chryl.mapper.GoodsMapper;
import com.chryl.po.ChrGoods;
import com.chryl.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public Map<String, Object> goodsList(PageVo page, ChrGoods chrGoods) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<ChrGoods> allGoods = goodsMapper.goodsList(chrGoods);
        PageInfo<ChrGoods> pageInfo = new PageInfo<>(allGoods);
        Map<String, Object> res = new HashMap<>();
        res.put("count", pageInfo.getTotal());
        res.put("data", allGoods);
        return res;
    }

    //条件查询:按类型查询
    public List<Map<String, String>> queryConditions() {
        //按类型查询
        List<Map<String, String>> resList = new ArrayList<>();
        List<Map<String, String>> typeList = goodsMapper.queryConditions2GoodsType();
        for (Map<String, String> dataMap : typeList) {
            String type = dataMap.get("goods_type");
            dataMap.put("key", type);
            if ("mi".equals(type)) {
                dataMap.put("display_name", "小米");
            } else if ("apple".equals(type)) {
                dataMap.put("display_name", "苹果");
            } else if ("huawei".equals(type)) {
                dataMap.put("display_name", "华为");
            } else if ("Nokia".equals(type)) {
                dataMap.put("display_name", "诺基亚");
            } else if ("samsung".equals(type)) {
                dataMap.put("display_name", "三星");
            }
            resList.add(dataMap);
        }
        return resList;
    }

    public String convertGoodsType() {
        return null;
    }

    //创建
    @Transactional
    public boolean createChrGoods(ChrGoods chrGoods) {
        int i = goodsMapper.saveGoods(chrGoods);
        if (i == 1) {
            return true;
        }
        return false;

    }

    @Transactional
    public boolean updateChrGoods(ChrGoods chrGoods) {
        if (StringUtils.isBlank(String.valueOf(chrGoods.getGoodsId()))) {
            return false;
        }
        int i = goodsMapper.updateChrGoods(chrGoods);
        if (i == 1) {
            return true;
        }
        return false;
    }
}
