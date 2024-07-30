package com.kaseihaku.bpm.app.one.workflow.feature.v1.n2_extend;

import com.kaseihaku.core.ppe.node.hook.AfterReceiveHook;

public class ExtendAfterReceiveHook implements AfterReceiveHook<ExtendPojo> {
    @Override
    public void handleAfterReceive(ExtendPojo extendPojo) {
        extendPojo.setTamperByServer(extendPojo.getTamperByServer() + "-alter by server");
    }
}
