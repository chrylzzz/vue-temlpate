package com.chryl.mapper;

import com.chryl.po.ChrGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Chr.yl on 2020/6/10.
 *
 * @author Chr.yl
 */
@Repository
public interface GoodsMapper
//        extends JpaRepository<ChrGoodsModel, Integer>
{

    List<ChrGoods> getAllGoods();

    List<ChrGoods> goodsList(@Param("chrGoods") ChrGoods chrGoods);

    List<Map<String, String>> queryConditions2GoodsType();

    int saveGoods(@Param("chrGoods") ChrGoods chrGoods);

    int updateChrGoods(@Param("chrGoods") ChrGoods chrGoods);

    int changeGoodsStatus(@Param("chrGoods") ChrGoods chrGoods);

    int deleteGoods(@Param("goodsId") String goodsId);
}
