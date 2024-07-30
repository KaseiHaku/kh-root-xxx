package com.kaseihaku.bpm.app.one.ctrl.dto;

import com.kaseihaku.core.infra.pojo.BaseDto;
import lombok.Data;

@Data
public class FeatureDto extends BaseDto {

    private Long procId; // 流程引擎中的流程 ID
    private Integer countSignTotal;
}
