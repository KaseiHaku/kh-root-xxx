package com.kaseihaku.bpm.app.one.workflow.feature.v1.n6_overrule;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OverrulePojo {

    @NotNull
    @PpeFormField(label = "是否驳回")
    private OverruleEnum overrule;
}
