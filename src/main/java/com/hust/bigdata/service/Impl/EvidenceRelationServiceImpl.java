package com.hust.bigdata.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.mapper.EvidenceRelationMapper;
import com.hust.bigdata.po.EvidenceRelation;
import com.hust.bigdata.service.EvidenceRelationService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class EvidenceRelationServiceImpl extends ServiceImpl<EvidenceRelationMapper, EvidenceRelation> implements EvidenceRelationService {

    @Autowired
    EvidenceRelationMapper evidenceRelationMapper;


    @Override
    public PageResult<EvidenceRelation> queryEvidenceRelationList(int pageNo, int pageSize, String id) {

        Page<EvidenceRelation> page = new Page<>(pageNo, pageSize);

        LambdaQueryWrapper<EvidenceRelation> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(StringUtils.isNotEmpty(id), EvidenceRelation::getMainEvidenceId, id);

        Page<EvidenceRelation> PageResult = evidenceRelationMapper.selectPage(page, queryWrapper);


        List<EvidenceRelation> item = PageResult.getRecords();
        long total = PageResult.getTotal();

        PageResult<EvidenceRelation> EvidenceRelationResult = new PageResult<>(item, total, pageNo, pageSize);
        return EvidenceRelationResult;


    }
}
