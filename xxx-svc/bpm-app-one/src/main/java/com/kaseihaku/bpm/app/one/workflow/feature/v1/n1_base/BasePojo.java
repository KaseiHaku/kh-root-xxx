package com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormGroup;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.vue.MultiLineStr;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@PpeFormGroup(value = "JavaBean 类型", fields = {"bean"})
public class BasePojo {
    @NotNull(message = "是否同意必填")
    @PpeFormField("是否同意")
    private Boolean yesOrNo;
    @PpeFormField("体重(斤)")
    private Integer weight;
    @PpeFormField("硬盘大小(Byte)")
    private Long hardDiskVolume;
    @NotNull(message = "距离必填")
    @PpeFormField("到 M78 星云的距离(米)")
    private BigInteger distanceOfM78Nebula;
    @PpeFormField("股票金额(元)")
    private BigDecimal stockAmount;
    @PpeFormField("电话号码")
    private String phone;
    @PpeFormField(label = "备注", vueComponent = MultiLineStr.class)
    private String remark;
    @PpeFormField(label="生日", vueComponent = LocalDate.class)
    private Instant birthday;
    @PpeFormField(label="令牌过期时间", vueComponent = LocalDateTime.class)
    private Instant tokenExpireTime;
    @PpeFormField(label="季节v1", enumerate=Season.V1.class)
    private Season season;
    @PpeFormField(label="内嵌 JavaBean 类型")
    private NestedAttr bean;
    @PpeFormField(label="基本类型的数组")
    private String[] strAry;
    @PpeFormField(label="Bean 的容器")
    private Set<NestedAttr> beanSet;
    @PpeFormField(label="Bean 数组的容器")
    private Set<NestedAttr[]> beanArySet;

}
