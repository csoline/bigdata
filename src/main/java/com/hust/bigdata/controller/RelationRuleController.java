package com.hust.bigdata.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.po.RelationRule;
import com.hust.bigdata.service.RelationRuleService;
import com.hust.bigdata.utils.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class RelationRuleController {

    @Autowired
    RelationRuleService relationRuleService;


     //新增关联规则
    @PostMapping("/relation")
    public RelationRule save(@RequestBody RelationRule relationRule){

       relationRuleService.save(relationRule);

       return relationRule;
    }

    //删除
    @DeleteMapping("/relation")
    public String delete(String id){
        relationRuleService.removeById(id);
        return id;
    }


    //更新
    @PutMapping("/relation")
    public RelationRule update(@RequestBody RelationRule relationRule){
        relationRuleService.updateById(relationRule);

        return relationRule;
    }

    //查询关联规则
    @GetMapping("/relation/list")
    public List<RelationRule> list(String s){

        List<String> sqlFields = new ArrayList<>();
        Class<RelationRule> clazz = null;
        try {
            clazz = (Class<RelationRule>) Class.forName("com.hust.bigdata.po.RelationRule");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取所有的属性，包括私有属性
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            sqlFields.add(field.getName());
        }

//        Class<RelationRule> clazz = null;
//        ReflectionUtil reflectionUtil = new ReflectionUtil();
//        try {
//             clazz = (Class<RelationRule>) Class.forName("com.hust.bigdata.po.RelationRule");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        List<String> sqlFields = reflectionUtil.getFieldNames(clazz);

        LambdaQueryWrapper<RelationRule> queryWrapper = new LambdaQueryWrapper<>();

//        queryWrapper.like(relationRule.getRelationName() != null, RelationRule::getRelationName, relationRule.getRelationName());

        for (String field : sqlFields) {
            String column = StringUtils.camelToUnderline(field);
            queryWrapper.or(wrapper -> wrapper.apply(column + " like {0}", "%" + s + "%"));

        }


        queryWrapper.orderByDesc(RelationRule::getCreateTime);

        List<RelationRule> list = relationRuleService.list(queryWrapper);


        return list;
        //relationRuleService.list()


    }


    //分页查询
    @GetMapping("/relation/page")
    public PageResult<RelationRule> list(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) String s){
        PageResult<RelationRule> relationRuleServicePageResult = relationRuleService.queryRelationRuleList(pageNo, pageSize, s);
        return relationRuleServicePageResult;
    }

    
}
