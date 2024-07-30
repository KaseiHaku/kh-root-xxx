package com.kaseihaku.bpm.app.one.workflow.feature.v1.n5_join;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import lombok.Data;

@Data
public class CounterSignJoinPojo {

    @PpeFormField(label = "会签允许使用数量的总数", disabledAnyWay = true)
    private Integer countSignTotal;
}
