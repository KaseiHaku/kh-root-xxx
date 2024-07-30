package com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class NestedAttr {

    @NotNull(message = "decimal 字段不能为空")
    private BigDecimal decimal;

    @PpeFormField("时间戳数组")
    private List<Instant> instantList;
}
