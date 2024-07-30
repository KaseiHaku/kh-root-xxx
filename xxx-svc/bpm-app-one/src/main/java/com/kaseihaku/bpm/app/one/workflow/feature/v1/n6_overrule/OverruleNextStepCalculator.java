package com.kaseihaku.bpm.app.one.workflow.feature.v1.n6_overrule;

import com.kaseihaku.boot.starter.spring.exception.DelegateBizExBuilder;
import com.kaseihaku.bpm.app.one.workflow.feature.FeatureNodeBrand;
import com.kaseihaku.bpm.starter.ppe.i18n.BpmPpeI18nKey;
import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.node.PpeNodeReadSvc;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculator;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculatorDto;

import java.util.Set;

public class OverruleNextStepCalculator implements NextStepCalculator<OverrulePojo> {

    @Override
    public Set<PpeToDoDto> calculate(NextStepCalculatorDto<OverrulePojo> dto, NodeCommitContext ctx) {

        OverrulePojo pojoIns = dto.getCurPojoFqcnIns();

        // 驳回到首环节
        if (OverruleEnum.yes.equals(pojoIns.getOverrule())) {
            // 获取首环节处理人
            PpeNodeReadSvc nodeReadSvc = dto.getCurPpe().getNodeReadSvc();
            Long firstNodeApproverHomGid = nodeReadSvc.closestAncestors(dto.getCurNodeIns().getId(), FeatureNodeBrand.V1.base)
                .stream().findFirst().map(item -> item.getPrivCreatorHgid())
                .orElseThrow(() -> DelegateBizExBuilder.ins().build(BpmPpeI18nKey.ex_biz, "找不到首环节"));


            PpeToDoDto overruleToDo = new PpeToDoDto();
            overruleToDo.setProcId(dto.getCurProcIns().getId());
            overruleToDo.setNodeId(dto.getCurNodeIns().getId());
            overruleToDo.setNextApproverHgid(firstNodeApproverHomGid);
            overruleToDo.setNextNodeMetaBrand(FeatureNodeBrand.V1.base);
            return Set.of(overruleToDo);
        }

        // 不设置任何待办，等价于 结束流程
        return Set.of();
    }
}
