package com.hust.bigdata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.po.EvidenceRelation;

public interface EvidenceRelationService extends IService<EvidenceRelation> {

    public PageResult<EvidenceRelation> queryEvidenceRelationList(int pageNo, int pageSize, String id);

}
