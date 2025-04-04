package com.kaseihaku.bpm.app.one.ctrl;

import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureDto;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQry;
import com.kaseihaku.bpm.app.one.svc.FeatureReadSvc;
import com.kaseihaku.bpm.app.one.svc.FeatureWriteSvc;
import com.kaseihaku.core.infra.pojo.Paged;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Feature")
@RequiredArgsConstructor
public class FeatureCtrl {
    private final FeatureReadSvc readSvc;
    private final FeatureWriteSvc writeSvc;

    @PostMapping("")
    public FeatureDto post(@RequestBody FeatureDto dto){
        return writeSvc.create(dto);
    }

    @DeleteMapping("")
    public FeatureDto delete(FeatureDto dto){
        return writeSvc.logicDel(dto).orElse(null);
    }

    @PatchMapping("")
    public FeatureDto patch(@RequestBody FeatureDto dto){
        return writeSvc.updateById(dto).orElse(null);
    }

    @GetMapping("")
    public FeatureDto get(Long id){

        return readSvc.queryById(id).orElse(null);

    }


    @PutMapping("")
    public List<FeatureDto> put(@RequestBody Paged<FeatureQry> qryDto) {
        List<FeatureDto> query = readSvc.query(qryDto);
        return query;
    }



}
