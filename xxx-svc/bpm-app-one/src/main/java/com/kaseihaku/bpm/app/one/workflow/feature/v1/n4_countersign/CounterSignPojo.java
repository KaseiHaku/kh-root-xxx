package com.kaseihaku.bpm.app.one.workflow.feature.v1.n4_countersign;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CounterSignPojo {

    @NotNull(message = "allow count 必填")
    @PpeFormField("允许使用的数量")
    private Integer allowCount;
}
