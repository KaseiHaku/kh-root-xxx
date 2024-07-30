package com.kaseihaku.bpm.app.one.workflow.feature.v2.n1_base;

import com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base.BasePojo;
import com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base.Season;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.*;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.vue.MultiFile;
import lombok.Data;

import java.io.File;
import java.util.Set;

/**
 * POJO 渲染引擎 可用类型演示
 * */
@Data
/**
 * 添加该注解，会在启动时，对当前 POJO 进行预解析，提高前端渲染速度
 * @tips 废弃/历史 流程的 POJO 可以删除，减小缓存占用
 * */
@PpeAotResolve
/**
 * @tips 明确指定 默认组 会导致没有分组的字段不显示
 * @tips 默认组 不受 order 影响，永远在最前面
 * */
// @PpeFormGroup(fields = {"hardDiskVolume"})
@PpeFormGroup(value = "单字段，分多组，1", fields = {"yesOrNo"})
@PpeFormGroup(value = "单字段，分多组，2", fields = {"yesOrNo"}, order = -1)
@PpeFormGroup(value = "JavaBean 类型", fields = {"bean"})
@PpeFormGroup(value = "多值 类型", fields = {"strAry", "beanSet", "beanArySet"})
@PpeFormGroup(value = "文件", fields = {"files", "file"}, order = -2, fieldsSortType = FieldsSortTypeKeep.class)
public class BaseV2Pojo extends BasePojo {

    @PpeFormField(label="季节v2", enumerate= Season.V2.class)
    private Season season;

    @PpeFormField(label="单文件上传", vueComponent = File.class)
    private String file;
    @PpeFormField(label="多文件上传", vueComponent = MultiFile.class)
    private Set<String> files;
}
