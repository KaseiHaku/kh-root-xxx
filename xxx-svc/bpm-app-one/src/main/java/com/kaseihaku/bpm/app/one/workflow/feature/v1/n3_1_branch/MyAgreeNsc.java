package com.kaseihaku.bpm.app.one.workflow.feature.v1.n3_1_branch;

import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculator;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculatorDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class MyAgreeNsc implements NextStepCalculator<MyAgreePojo> {

    @Override
    public Set<PpeToDoDto> calculate(NextStepCalculatorDto<MyAgreePojo> dto, NodeCommitContext ctx) {
        if(!eligibleForMerge()){
            return null;
        }

        return null;
    }


    /**
     * 判断当前流程状态，是否符合合并分支合并条件
     * @tips 当前方法没有参数，可以按需求修改
     * @trap 当前流程触发 merge 逻辑放到 branch2 中，
     *       实际开发中不能这么写，会导致当前 branch1 必须在 branch2 之前完成，流程才能下行
     * */
    private boolean eligibleForMerge(){
        return false;
    }
}
