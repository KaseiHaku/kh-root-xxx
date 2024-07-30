package com.kaseihaku.bpm.app.one.workflow.feature.v1.n5_join;

import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureDto;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQryDto;
import com.kaseihaku.bpm.app.one.svc.FeatureReadSvc;
import com.kaseihaku.core.ppe.node.hook.AfterRetrieveHookCtx;
import com.kaseihaku.core.ppe.node.hook.AfterRetrieveHook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class JoinAfterRetrieveHook implements AfterRetrieveHook<CounterSignJoinPojo> {


    private final FeatureReadSvc featureReadSvc;

    @Override
    public CounterSignJoinPojo handleAfterRetrieve(AfterRetrieveHookCtx hookCtx, CounterSignJoinPojo extendPojo) {
        log.atInfo().log("获取会签提交总数");
        FeatureQryDto qryDto = new FeatureQryDto();
        qryDto.setProcId(hookCtx.getProcId());
        Integer countSignTotal = featureReadSvc.queryOne(qryDto).map(FeatureDto::getCountSignTotal).get();
        extendPojo.setCountSignTotal(countSignTotal);
        return null;
    }
}
