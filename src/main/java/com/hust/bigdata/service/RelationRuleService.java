package com.hust.bigdata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.po.EvidenceRelation;
import com.hust.bigdata.po.RelationRule;

public interface RelationRuleService extends IService<RelationRule> {

    PageResult<RelationRule> queryRelationRuleList(int pageNo, int pageSize, String s);

}
