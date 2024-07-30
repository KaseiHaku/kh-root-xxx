package com.kaseihaku.bpm.app.one.workflow.feature.v1.n2_extend;

import com.kaseihaku.core.infra.design.chain.Chain;
import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.node.hook.AfterPersistenceHook;
import com.kaseihaku.core.ppe.node.hook.AfterPersistenceHookChain;
import com.kaseihaku.core.ppe.node.hook.ApHookCtx;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExtendAfterPersistenceHook implements AfterPersistenceHook<ExtendPojo> {

    @Override
    public Void handleAfterArchived(ApHookCtx<ExtendPojo> hookCtx, NodeCommitContext ctx, AfterPersistenceHookChain chain) {
        ExtendPojo extendPojo = hookCtx.getCurPojoFqcnIns();
        log.info("ExtendAfterPersistenceHook: 这里可以执行入 关系型数据库，例如: MySQL, PostgreSQL");
        return null;
    }
}
