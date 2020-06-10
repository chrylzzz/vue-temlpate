package com.chryl.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chr.yl on 2020/6/7.
 *
 * @author Chr.yl
 */
@Entity
@Table(name = "chr_role")
@Data
public class ChrRole implements Serializable {
    private static final long serialVersionUID = 1637772700686061620L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "desc")
    private String desc;

    @Column(name = "is_use")
    private String isUse;
}
