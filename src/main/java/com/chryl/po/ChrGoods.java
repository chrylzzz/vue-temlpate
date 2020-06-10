package com.chryl.po;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Chr.yl on 2020/6/10.
 *
 * @author Chr.yl
 */
@Entity
@Table(name = "chr_goods")
@Data
public class ChrGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private String goodsPrice;

}
