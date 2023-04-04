package com.exchange.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exchange.vo.ApiResponse;
import com.exchange.entity.Notice;
import com.exchange.service.NoticeService;
import com.exchange.util.shiro.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/notices")
    public ApiResponse list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 3);
        IPage pageData = noticeService.page(page, new QueryWrapper<Notice>().orderByDesc("created"));
        return ApiResponse.success(pageData);
    }

    @GetMapping("/notice/{id}")
    public ApiResponse detail(@PathVariable(name = "id") Long id) {
        Notice notice = noticeService.getById(id);
        Assert.notNull(notice, "该博客已被删除");

        return ApiResponse.success(notice);
    }

    @RequiresAuthentication
    @PostMapping("/notice/edit")
    public ApiResponse edit(@Validated @RequestBody Notice notice) {

//        Assert.isTrue(false, "公开版不能任意编辑！");

        Notice temp = null;
        if(notice.getId() != null) {
            temp = noticeService.getById(notice.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());

            //assert最好用equal
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        } else {

            temp = new Notice();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(notice, temp, "id", "userId", "created", "status");
        noticeService.saveOrUpdate(temp);

        return ApiResponse.success(null);
    }
}
