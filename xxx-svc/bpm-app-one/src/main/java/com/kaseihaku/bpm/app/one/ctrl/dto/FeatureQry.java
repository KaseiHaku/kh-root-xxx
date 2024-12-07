package com.kaseihaku.bpm.app.one.ctrl.dto;

import com.kaseihaku.core.infra.pojo.BaseQry;
import lombok.Data;

@Data
public class FeatureQry extends BaseQry {

    private Long procId;
    private Integer countSignTotal;
}
