package com.hust.bigdata.service;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust.bigdata.common.R;
import com.hust.bigdata.mapper.*;
import com.hust.bigdata.po.*;
import com.hust.bigdata.utils.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.Relation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

    @Autowired
    EvidenceRelationMapper evidenceRelationMapper;

    @Autowired
    RelationRuleMapper relationRuleMapper;

    @Autowired
    LogMapper logMapper;

    @Autowired
    EvidenceMapper evidenceMapper;

    @Autowired
    EvidenceChainMapper evidenceChainMapper;




    public boolean modify(int tableId, int itemId, String json) {

        switch (tableId) {

            //日志
            case 1:
                Log log = JSON.parseObject(json, Log.class);
                log.setId(itemId);
                return logMapper.updateById(log) > 0;

            //证据
            case 2:
                Evidence evidence = JSON.parseObject(json, Evidence.class);
                evidence.setId(itemId);
                return evidenceMapper.updateById(evidence) > 0;

            //证据关联
            case 3:
                EvidenceRelation evidenceRelation = JSON.parseObject(json, EvidenceRelation.class);
                evidenceRelation.setId(String.valueOf(itemId));
                return evidenceRelationMapper.updateById(evidenceRelation) > 0;

            //证据链
            case 4:
                EvidenceChain evidenceChain = JSON.parseObject(json, EvidenceChain.class);
                evidenceChain.setId(itemId);
                return evidenceChainMapper.updateById(evidenceChain) > 0;

            //关联规则
            case 5:
                RelationRule rule = JSON.parseObject(json, RelationRule.class);
                rule.setId(String.valueOf(itemId));
                return relationRuleMapper.updateById(rule) > 0;
            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }


    public <T> R<T> detail(int tableId, int itemId) {
        switch (tableId) {

            //日志
            case 1:
                Log log = logMapper.selectById(itemId);
                R<Log> logResult = R.success(log);
                return (R<T>) logResult;

            //证据
            case 2:



                //证据关联
            case 3:


                //证据链
            case 4:

                //关联规则
            case 5:
                RelationRule relationRule = relationRuleMapper.selectById(itemId);
                R<RelationRule> relationRuleResult = R.success(relationRule);
                return (R<T>) relationRuleResult;

            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }


    public <T> R<T> show(int tableId, int pageNo, int pageSize, String s) {
        switch (tableId) {

            //日志
            case 1:


                //证据
            case 2:


                //证据关联
            case 3:


                //证据链
            case 4:

                //关联规则
            case 5:
                Page<RelationRule> relationRulePage = new Page<>(pageNo, pageSize);
                //LambdaQueryWrapper<RelationRule> relationRuleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                QueryWrapper<RelationRule> relationRuleQueryWrapper = new QueryWrapper<>();
                if(StringUtils.isNotBlank(s)){
                    Class<RelationRule> clazz = null;
                    try {
                        clazz = (Class<RelationRule>) Class.forName("com.hust.bigdata.po.RelationRule");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    // 获取所有的属性，包括私有属性
                    List<String> sqlFields = ReflectionUtil.getFieldNames(clazz);

                    //模糊查询
                    for (String field : sqlFields) {
                        String column = StringUtils.camelToUnderline(field);
                        //relationRuleLambdaQueryWrapper.or(wrapper -> wrapper.apply(column + " like {0}", "%" + s + "%"));
                        relationRuleQueryWrapper.or(wrapper -> wrapper.like(column, s));
                    }
                }
                Page<RelationRule> relationRulePageResult = relationRuleMapper.selectPage(relationRulePage, relationRuleQueryWrapper);
                return (R<T>) R.success(relationRulePageResult);

            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }

    public boolean delete(int tableId, int itemId){
        switch (tableId) {

            //日志
            case 1:


                //证据
            case 2:


                //证据关联
            case 3:


                //证据链
            case 4:

                //关联规则
            case 5:
                 return relationRuleMapper.deleteById(itemId) > 0;

            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }

    public boolean addNew(int tableId,String json){
        switch (tableId) {
            //关联规则表
            case 5:
                RelationRule rule = JSON.parseObject(json, RelationRule.class);
                return relationRuleMapper.insert(rule) > 0;

            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }

    public <T> R<T> search(int tableId, int pageNo, int pageSize, String s){
        switch (tableId) {

            //日志
            case 1:


                //证据
            case 2:


                //证据关联
            case 3:


                //证据链
            case 4:

                //关联规则
            case 5:
                //LambdaQueryWrapper<RelationRule> relationRuleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                QueryWrapper<RelationRule> relationRuleQueryWrapper = new QueryWrapper<>();
                if(StringUtils.isNotBlank(s)){
                    String[] conditions = s.split("(?<=&&|\\|\\|)|(?=&&|\\|\\|)");
                    for(String condition: conditions){
                        if(condition.equals("&&")){
                            continue;
                        }
                        if(condition.equals("||")){
                            relationRuleQueryWrapper.or();
                            continue;
                        }
                        String[] parts = condition.split("==|>=|<=|>|<|like");
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid query condition: " + condition);
                        }
                        String propName = parts[0].trim();
                        propName = StringUtils.camelToUnderline(propName);
                        String operator = condition.substring(parts[0].length(), condition.length() - parts[1].length()).trim();
                        String valueStr = parts[1].trim();
                        switch (operator) {
                            case "==":
                                relationRuleQueryWrapper.eq(propName, valueStr);
                                break;
                            case ">=":
                                relationRuleQueryWrapper.ge(propName, valueStr);
                                break;
                            case "<=":
                                relationRuleQueryWrapper.le(propName, valueStr);
                                break;
                            case "!=":
                                relationRuleQueryWrapper.ne(propName, valueStr);
                                break;
                            case ">":
                                relationRuleQueryWrapper.gt(propName, valueStr);
                                break;
                            case "<":
                                relationRuleQueryWrapper.lt(propName, valueStr);
                                break;
                            case "like":
                                relationRuleQueryWrapper.like(propName, valueStr);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid operator: " + operator);
                        }
                    }
                }
                Page<RelationRule> relationRulePage = new Page<>(pageNo, pageSize);
                Page<RelationRule> relationRulePageResult = relationRuleMapper.selectPage(relationRulePage, relationRuleQueryWrapper);
                return (R<T>) R.success(relationRulePageResult);

            default:
                throw new RuntimeException("未知的tableId: " + tableId);
        }
    }

}
