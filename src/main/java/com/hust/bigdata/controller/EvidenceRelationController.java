package com.hust.bigdata.controller;


import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.po.EvidenceRelation;
import com.hust.bigdata.service.EvidenceRelationService;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EvidenceRelationController {

    @Autowired
    EvidenceRelationService evidenceRelationService;

    @GetMapping("/evidence/page")
    public PageResult<EvidenceRelation> list(int pageNo, int pageSize, String id){
        PageResult<EvidenceRelation> evidenceRelationPageResult = evidenceRelationService.queryEvidenceRelationList(pageNo, pageSize, id);
        return evidenceRelationPageResult;
    }

}
