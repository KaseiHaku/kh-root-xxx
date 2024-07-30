package com.kaseihaku.bpm.app.one.workflow.feature.v1.n5_join;

import com.kaseihaku.boot.starter.spring.exception.DelegateBizExBuilder;
import com.kaseihaku.bpm.app.one.workflow.feature.FeatureNodeBrand;
import com.kaseihaku.bpm.starter.ppe.i18n.BpmPpeI18nKey;
import com.kaseihaku.cloud.starter.basic.constant.CloudImmutableConst;
import com.kaseihaku.core.ppe.engine.PpeFacade;
import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.node.PpeNodeDto;
import com.kaseihaku.core.ppe.node.PpeNodeReadSvc;
import com.kaseihaku.core.ppe.node.meta.PpeNodeMetaDto;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculator;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculatorDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Node {@link FeatureNodeBrand.V1#branch1} 和 {@link FeatureNodeBrand.V1#join} 共用当前 待办计算器
 * */
@Slf4j
public class CounterSignJoinNsc implements NextStepCalculator<CounterSignJoinPojo> {


    @Override
    public Set<PpeToDoDto> calculate(NextStepCalculatorDto<CounterSignJoinPojo> dto, NodeCommitContext ctx) {

        log.info("这里将从 extend 分出的两个 branch 合并回来");

        Long curNodeId = dto.getCurNodeIns().getId();
        PpeFacade ppe = dto.getCurPpe();
        PpeNodeMetaDto curNodeMeta = dto.getCurNodeMeta();
        PpeNodeReadSvc nodeReadSvc = ppe.getNodeReadSvc();

        // 获取两个 branch 共同的父节点
        List<PpeNodeDto> extendNodes = nodeReadSvc.closestAncestors(curNodeId, FeatureNodeBrand.V1.extend);

        if(CollectionUtils.isEmpty(extendNodes)){
            throw DelegateBizExBuilder.ins().build(BpmPpeI18nKey.ex_biz, "找不到分支起始点，流程走向有问题");
        }


        PpeNodeDto parentNode = extendNodes.get(0);  // extend 没有多实例的情况， 所以直接获取第一个即可


        // 判断当前在哪个 branch 上
        PpeNodeDto anotherNode = null;
        if (FeatureNodeBrand.V1.branch1.equals(curNodeMeta.getBrand())) {
            List<PpeNodeDto> ppeNodeDtos = nodeReadSvc.furthestDescendant(parentNode.getId(), FeatureNodeBrand.V1.join);
            /* 由于 join 节点没有 驳回操作，所以最多只会存在一个实例，因此这里只需判断是否存在即可
             * 如果存在 驳回操作，则需额外判断，具体判断逻辑根据业务决定
             * */
            if (CollectionUtils.isEmpty(ppeNodeDtos)) {
                log.atInfo().log("branch2 分支还没有下行到 join 节点，join 节点还未提交");
                return Set.of();
            }
            anotherNode = ppeNodeDtos.get(0);
        } else if (FeatureNodeBrand.V1.join.equals(curNodeMeta.getBrand())) {
            List<PpeNodeDto> ppeNodeDtos = nodeReadSvc.furthestDescendant(parentNode.getId(), FeatureNodeBrand.V1.branch1);
            if (CollectionUtils.isEmpty(ppeNodeDtos)) {
                log.atInfo().log("branch1 节点还未提交");
                return Set.of();
            }
            anotherNode = ppeNodeDtos.get(0);
        } else {
            throw DelegateBizExBuilder.ins().build(BpmPpeI18nKey.ex_biz, "无法确定当前提交所在的流程分支");
        }



        // 执行合并
        PpeToDoDto newToDo = new PpeToDoDto();
        newToDo.setNextNodeMetaBrand(FeatureNodeBrand.V1.overrule);
        newToDo.setNextApproverHgid(CloudImmutableConst.PreBuryGroup.Homonym_0);

        List<PpeToDoDto> newToDos = ppe.nodeMerge(dto.getCurProcIns().getId(), Set.of(curNodeId, anotherNode.getId()),
            List.of(newToDo));
        return Set.copyOf(newToDos);
    }
}
