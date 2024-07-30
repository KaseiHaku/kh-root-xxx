package com.kaseihaku.bpm.app.one.workflow.feature.v1.n2_extend;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeAotResolve;
import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import com.kaseihaku.core.ppe.node.hook.AfterPersistenceHook;
import com.kaseihaku.core.ppe.node.hook.AfterReceiveHook;
import com.kaseihaku.core.ppe.node.hook.AfterRetrieveHook;
import com.kaseihaku.core.ppe.node.meta.RenderEngineParams;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculator;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * 扩展点演示(Native 渲染引擎默认参数，Hook，待办计算器):
 * {@linkplain AfterReceiveHook 修改提交数据}，
 * {@linkplain NextStepCalculator 待办计算器}，
 * {@linkplain AfterPersistenceHook 提交处理完毕后置处理}，
 * {@linkplain AfterRetrieveHook 渲染引擎数据前置处理}，
 *
 *
 * @tips 当当前节点使用 NativeRe 且 没有配置 {@link RenderEngineParams#viewPath} 时，
 *       那么默认取当前 pojo 全类名，作为 {@link RenderEngineParams#viewPath}
 *       即: 相当于配置了以下 NativeRe 参数
 *       {
 *         "@class": "com.kaseihaku.core.ppe.node.meta.RenderEngineParams",
 *         "viewPath": "com/kaseihaku/bpm/app/one/workflow/feature/v1/n2_extend/ExtendPojo.vue",
 *       }
 *
 *
 * */
@Data
@PpeAotResolve
public class ExtendPojo {

    @PpeFormField("提交后会被 AfterReceiveHook 修改的")
    private String tamperByServer;

    @PpeFormField(value = "渲染时由 AfterRetrieveHook 生成的", disabledAnyWay = true)
    private String genByRetrieveHook;
}
