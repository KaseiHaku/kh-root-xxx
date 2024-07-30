package com.kaseihaku.bpm.app.one.ctrl.dto;

import com.kaseihaku.core.infra.pojo.BaseQueryDto;
import lombok.Data;

@Data
public class FeatureQryDto extends BaseQueryDto {

    private Long procId;
    private Integer countSignTotal;
}
