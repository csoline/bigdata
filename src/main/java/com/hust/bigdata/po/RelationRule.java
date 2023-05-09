package com.hust.bigdata.po;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RelationRule {

    private String id;

    private String relationName;

    private String eviAttribute;

    private LocalDateTime createTime;

    private Long state;

    private String creator;

    
}
