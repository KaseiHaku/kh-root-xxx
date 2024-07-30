package com.kaseihaku.bpm.app.one.workflow.feature.v1.n2_extend;

import com.kaseihaku.core.ppe.node.hook.AfterRetrieveHookCtx;
import com.kaseihaku.core.ppe.node.hook.AfterRetrieveHook;

public class ExtendAfterRetrieveHook implements AfterRetrieveHook<ExtendPojo> {


    @Override
    public ExtendPojo handleAfterRetrieve(AfterRetrieveHookCtx hookCtx, ExtendPojo extendPojo) {
        extendPojo.setGenByRetrieveHook("由 渲染引擎数据前置处理 钩子赋值");
        return null;
    }
}
