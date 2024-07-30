package com.kaseihaku.bpm.app.one;

import com.kaseihaku.boot.starter.jackson.JacksonUtil;
import com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base.Season;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.DefaultPojoSchemaResolver;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.SimplePojoSchemaResolver;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormGroup;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.result.PropResolveResult;
import lombok.Data;
import org.junit.jupiter.api.Test;

class PpePojoReTest {

    @Data
    @PpeFormGroup(value = "多值 类型", fields = {"phone"})
    public static class A {
        @PpeFormField("电话号码")
        private String phone;
        @PpeFormField(label="季节v1", enumerate= Season.V1.class)
        private Season season;
    }
    @Data
    @PpeFormGroup(value = "多值 类型", fields = {"phone", "season"})
    public static class B {
        @PpeFormField(label="季节v2", enumerate=Season.V2.class)
        private Season season;
    }


    @Test
    void resolve() {

        PropResolveResult resolve = new SimplePojoSchemaResolver().resolve(B.class);
        String s = JacksonUtil.writeValueAsString(resolve);
        System.out.println(s);
    }
}
