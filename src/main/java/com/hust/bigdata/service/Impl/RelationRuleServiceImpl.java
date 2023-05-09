package com.hust.bigdata.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust.bigdata.common.PageResult;
import com.hust.bigdata.mapper.RelationRuleMapper;
import com.hust.bigdata.po.RelationRule;
import com.hust.bigdata.service.RelationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Service
public class RelationRuleServiceImpl extends ServiceImpl<RelationRuleMapper, RelationRule> implements RelationRuleService {
    @Autowired
    RelationRuleMapper relationRuleMapper;

    public PageResult<RelationRule> queryRelationRuleList(int pageNo, int pageSize, String s){

        Page<RelationRule> page = new Page<>(pageNo, pageSize);

        LambdaQueryWrapper<RelationRule> queryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(s)){
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

            for (String field : sqlFields) {
                String column = StringUtils.camelToUnderline(field);
                queryWrapper.or(wrapper -> wrapper.apply(column + " like {0}", "%" + s + "%"));
            }
        }

        queryWrapper.orderByDesc(RelationRule::getCreateTime);


        //queryWrapper.eq(StringUtils.isNotEmpty(relationName), RelationRule::getRelationName, relationName);

        Page<RelationRule> PageResult = relationRuleMapper.selectPage(page, queryWrapper);

        List<RelationRule> item = PageResult.getRecords();
        long total = PageResult.getTotal();

        PageResult<RelationRule> RelationRuleResult = new PageResult<>(item, total, pageNo, pageSize);
        return RelationRuleResult;
    }


}
