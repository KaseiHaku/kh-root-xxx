package com.xxx.bpm.app.xxx.workflow.video.v1.n1_first;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import lombok.Data;

@Data
public class FirstPojo {

    @PpeFormField(label = "字段名")
    private Integer ggh;


}
