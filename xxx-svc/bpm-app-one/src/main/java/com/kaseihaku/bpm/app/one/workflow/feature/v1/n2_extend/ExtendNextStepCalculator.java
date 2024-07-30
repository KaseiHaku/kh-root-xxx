package com.kaseihaku.bpm.app.one.workflow.feature.v1.n2_extend;

import com.kaseihaku.bpm.app.one.workflow.feature.FeatureNodeBrand;
import com.kaseihaku.cloud.starter.basic.constant.CloudImmutableConst;
import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculator;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculatorDto;

import java.util.Set;

public class ExtendNextStepCalculator implements NextStepCalculator<ExtendPojo> {

    @Override
    public Set<PpeToDoDto> calculate(NextStepCalculatorDto<ExtendPojo> dto, NodeCommitContext ctx) {

        PpeToDoDto toDo1 = new PpeToDoDto();
        toDo1.setProcId(dto.getCurProcIns().getId());
        toDo1.setNodeId(dto.getCurNodeIns().getId());
        toDo1.setNextApproverHgid(CloudImmutableConst.PreBuryGroup.Root);
        toDo1.setNextNodeMetaBrand(FeatureNodeBrand.V1.branch1);


        PpeToDoDto toDo2 = new PpeToDoDto();
        toDo2.setProcId(dto.getCurProcIns().getId());
        toDo2.setNodeId(dto.getCurNodeIns().getId());
        toDo2.setNextApproverHgid(CloudImmutableConst.PreBuryGroup.Root);
        toDo2.setNextNodeMetaBrand(FeatureNodeBrand.V1.branch2);

        return Set.of(toDo1, toDo2);
    }
}
