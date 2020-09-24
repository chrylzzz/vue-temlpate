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


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chryl.core.response.ReturnResult;
import com.chryl.quartz.model.ScheduleJobLog;
import com.chryl.quartz.service.ScheduleJobLogService;
import com.chryl.quartz.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务日志
 *
 * @author lgh
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @GetMapping("/page")
//	@PreAuthorize("@pms.hasPermission('sys:schedule:log')")
//    public ResponseEntity<IPage<ScheduleJobLog>> page(Long jobId, PageParam<ScheduleJobLog> page) {
    public Object page(Long jobId, PageParam<ScheduleJobLog> page) {
        IPage<ScheduleJobLog> list = scheduleJobLogService.page(page, new LambdaQueryWrapper<ScheduleJobLog>().eq(jobId != null, ScheduleJobLog::getJobId, jobId));
//        return ResponseEntity.ok(list);
        return ReturnResult.create(list);
    }
}
