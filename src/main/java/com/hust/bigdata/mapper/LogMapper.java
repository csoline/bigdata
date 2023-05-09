package com.hust.bigdata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust.bigdata.po.Log;
import com.hust.bigdata.po.RelationRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
}
