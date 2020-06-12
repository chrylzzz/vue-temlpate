package com.chryl.controller;

import com.alibaba.fastjson.JSONObject;
import com.chryl.core.response.ReturnResult;
import com.chryl.po.ChrGoods;
import com.chryl.service.GoodsService;
import com.chryl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chr.yl on 2020/6/10.
 *
 * @author Chr.yl
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/create")
    public Object create(@RequestBody ChrGoods chrGoods) {
        boolean res = goodsService.createChrGoods(chrGoods);
        if (res) {
            return ReturnResult.create(HttpStatus.OK);
        }
        return ReturnResult.create(null);
    }

    @PostMapping("/update")
    public Object update(@RequestBody ChrGoods chrGoods) {
        boolean res = goodsService.updateChrGoods(chrGoods);
        if (res) {
            return ReturnResult.create(HttpStatus.OK);
        }
        return ReturnResult.create(null);
    }

    //数据列表
    @GetMapping("/list")
    public Object list(PageVo page, ChrGoods chrGoods) {
//    public Object list(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return ReturnResult.create(goodsService.goodsList(page, chrGoods));
//        return ReturnResult.create(goodsService.goodsList(page, limit));
    }

    //获取查询框的查询条件
    @GetMapping("/conditions")
    public Object conditions() {
        List<Map<String, String>> queryConditions = goodsService.queryConditions();
        return ReturnResult.create(queryConditions);
    }

    //img
    @PostMapping("/upload")
    public Object upload(List<MultipartFile> fileList) {

        return ReturnResult.create(null);
    }

    @GetMapping("/showimg")
    public void showimg(@RequestParam(required = false) String path, HttpServletResponse response) {
        if (path == null || path.trim().length() == 0) {
            path = "/Users/chryl/upload/common/20200127/13881196847010329.jpg";
        }
        File file = new File(path);
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()
        ) {
            if (file.exists()) {

                byte[] bytes = new byte[inputStream.available()];
                int length = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    System.out.println("length in ::" + length);
                    //写出客户端
                    outputStream.write(bytes, 0, length);
                }
                System.out.println("length::" + length);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //echerts
    @GetMapping("/pieshow")
    public ReturnResult pieshow() {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> seriesData = new ArrayList<>();
        List<String> legendData = new ArrayList<>();

        List<ChrGoods> allGoods = goodsService.getAllGoods();
        for (ChrGoods allGood : allGoods) {
            legendData.add(allGood.getGoodsName());
            JSONObject seriesData_ = new JSONObject();
            seriesData_.put("name", allGood.getGoodsName());
            seriesData_.put("value", allGood.getGoodsPrice());
            seriesData.add(seriesData_);
        }
        jsonObject.put("legend", legendData);
        jsonObject.put("series", seriesData);
        return ReturnResult.create(jsonObject);
    }

    @GetMapping("/barshow")
    public ReturnResult barshow() {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> seriesData = new ArrayList<>();
        List<String> legendData = new ArrayList<>();

        List<ChrGoods> allGoods = goodsService.getAllGoods();
        for (ChrGoods allGood : allGoods) {
            legendData.add(allGood.getGoodsName());
            JSONObject seriesData_ = new JSONObject();
            seriesData_.put("name", allGood.getGoodsName());
            seriesData_.put("value", allGood.getGoodsPrice());
            seriesData.add(seriesData_);
        }
        jsonObject.put("legend", legendData);
        jsonObject.put("series", seriesData);
        return ReturnResult.create(jsonObject);
    }
}
