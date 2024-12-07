package com.kaseihaku.bpm.app.one.workflow.feature.v1.n4_countersign;

import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureDto;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQry;
import com.kaseihaku.bpm.app.one.svc.FeatureReadSvc;
import com.kaseihaku.bpm.app.one.svc.FeatureWriteSvc;
import com.kaseihaku.bpm.app.one.workflow.feature.FeatureNodeBrand;
import com.kaseihaku.core.ppe.engine.PpeFacade;
import com.kaseihaku.core.ppe.engine.ctx.NodeCommitContext;
import com.kaseihaku.core.ppe.node.PpeNodeDto;
import com.kaseihaku.core.ppe.todo.calculator.NextStepCalculatorDto;
import com.kaseihaku.core.ppe.todo.calculator.RatioDoneNextStepCalculator;
import com.kaseihaku.core.ppe.todo.PpeToDoDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CounterSignNsc extends RatioDoneNextStepCalculator<CounterSignPojo> {

    private final FeatureReadSvc featureReadSvc;
    private final FeatureWriteSvc featureWriteSvc;

    /**
     * 设置为 50%，表示 有一半同类型的 待办 完成，就执行 {@link #executeWhenMeetTheConditions} 方法
     * */
    @PostConstruct
    public void springInit(){
        this.ratio = 50;
    }

    @Override
    @Transactional
    public Set<PpeToDoDto> calculate(NextStepCalculatorDto<CounterSignPojo> dto, NodeCommitContext ctx) {
        Long curProcId = dto.getCurProcIns().getId();
        Integer allowCount = dto.getCurPojoFqcnIns().getAllowCount();

        log.info("会签结束后，合并所有 允许的数量入 关系型数据库(例如: MySQL, PostgreSQL); 并设置 待办，使流程下行");
        FeatureQry qryDto = new FeatureQry();
        qryDto.setProcId(curProcId);
        FeatureDto featureDto = featureReadSvc.queryOne(qryDto).orElseGet(() -> {
            FeatureDto createDto = new FeatureDto();
            createDto.setProcId(curProcId);
            return featureWriteSvc.create(createDto);
        });

        Integer countSignTotal = featureDto.getCountSignTotal();
        countSignTotal += allowCount;


        FeatureDto updateDto = new FeatureDto();
        updateDto.setId(featureDto.getId());
        updateDto.setVersion(featureDto.getVersion());
        updateDto.setCountSignTotal(countSignTotal);
        featureWriteSvc.updateById(updateDto);

        return super.calculate(dto, ctx);
    }

    @Override
    protected Set<PpeToDoDto> executeWhenMeetTheConditions(NextStepCalculatorDto<CounterSignPojo> dto, NodeCommitContext ctx){

        log.atInfo().log("符合会签结束条件，手动合并节点");


        PpeFacade curPpe = dto.getCurPpe(); // 流程引擎 实例
        // 获取所有需要 merge 的节点
        PpeNodeDto curNodeIns = dto.getCurNodeIns();  // 当前 待办提交 所生成的 新节点
        Long parentNodeId = dto.getCurToDo().getNodeId();
        Set<Long> mergeNodeParentsId = curPpe.getNodeReadSvc().children(parentNodeId).stream() // 查询所有父节点的所有子节点 包括自己
            .filter(nodeDto -> nodeDto.getMetaId().equals(curNodeIns.getMetaId())) // 过滤出和 当前节点 同类型的 兄弟节点
            .map(PpeNodeDto::getId).collect(Collectors.toSet());


        // 手动配置 下一步的待办，可以多个
        PpeToDoDto newMergeToDoDto = new PpeToDoDto();
        newMergeToDoDto.setNextNodeMetaBrand(FeatureNodeBrand.V1.join);
        newMergeToDoDto.setNextApproverHgid(-303100L); // root
        List<PpeToDoDto> mergeToDos = curPpe.nodeMerge(dto.getCurProcIns().getId(), mergeNodeParentsId, List.of(newMergeToDoDto));
        return Set.copyOf(mergeToDos);
    }
}
