/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.chryl.quartz.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chryl.core.response.ReturnResult;
import com.chryl.quartz.exception.YamiShopBindException;
import com.chryl.quartz.model.ScheduleJob;
import com.chryl.quartz.service.ScheduleJobService;
import com.chryl.quartz.util.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//import org.springframework.security.access.prepost.PreAuthorize;


/**
 * 定时任务
 *
 * @author lgh
 */
@Slf4j
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @GetMapping("/page")
//    public ResponseEntity<IPage<ScheduleJob>> page(String beanName, PageParam<ScheduleJob> page) {
    public Object page(String beanName, PageParam<ScheduleJob> page) {
        IPage<ScheduleJob> scheduleJobs = scheduleJobService.page(page, new LambdaQueryWrapper<ScheduleJob>().like(StrUtil.isNotBlank(beanName), ScheduleJob::getBeanName, beanName));
//        return ResponseEntity.ok(scheduleJobs);
        return ReturnResult.create(scheduleJobs);
    }

    /**
     * 定时任务信息
     */
    @GetMapping("/info/{jobId}")
//    public ResponseEntity<ScheduleJob> info(@PathVariable("jobId") Long jobId) {
    public Object info(@PathVariable("jobId") Long jobId) {
        ScheduleJob schedule = scheduleJobService.getById(jobId);
//        return ResponseEntity.ok(schedule);
        return ReturnResult.create(schedule);
    }

    /**
     * 保存定时任务
     */
    @PostMapping
//    public ResponseEntity<Void> save(@RequestBody @Valid ScheduleJob scheduleJob) {
    public Object save(@RequestBody @Valid ScheduleJob scheduleJob) {

        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()));
        if (dbAlikeCount > 0) {
            throw new YamiShopBindException("定时任务已存在");
        }

        scheduleJobService.saveAndStart(scheduleJob);
//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);
    }

    /**
     * 修改定时任务
     */
    @PutMapping
//    public ResponseEntity<Void> update(@RequestBody @Valid ScheduleJob scheduleJob) {
    public Object update(@RequestBody @Valid ScheduleJob scheduleJob) {

        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()).ne(ScheduleJob::getJobId, scheduleJob.getJobId()));
        if (dbAlikeCount > 0) {
            throw new YamiShopBindException("定时任务已存在");
        }

        scheduleJobService.updateScheduleJob(scheduleJob);

//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping
//    public ResponseEntity<Void> delete(@RequestBody Long[] jobIds) {
    public Object delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);

    }

    /**
     * 立即执行任务
     */
    @PostMapping("/run")
//    public ResponseEntity<Void> run(@RequestBody Long[] jobIds) {
    public Object run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);

    }

    /**
     * 暂停定时任务
     */
    @PostMapping("/pause")
//    public ResponseEntity<Void> pause(@RequestBody Long[] jobIds) {
    public Object pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);

    }

    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
//    public ResponseEntity<Void> resume(@RequestBody Long[] jobIds) {
    public Object resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
//        return ResponseEntity.ok().build();
        return ReturnResult.create(null);

    }

}
