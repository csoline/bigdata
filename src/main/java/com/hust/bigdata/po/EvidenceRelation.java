package com.hust.bigdata.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class EvidenceRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String relationRuleId;

    private String mainEvidenceId;

    private String evidenceArr;

    private String alarmId;

    private LocalDateTime createTime;

    private Long state;

    private String creator;

    private LocalDateTime deleteTime;

}
