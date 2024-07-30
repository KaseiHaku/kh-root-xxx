package com.kaseihaku.bpm.app.one.repo.dao.feature;

import com.kaseihaku.boot.starter.orika.pkg.arch.AbstractBaseWriteSvc;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureDto;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQryDto;
import com.kaseihaku.bpm.app.one.svc.FeatureWriteSvc;
import com.kaseihaku.core.infra.pkg.arch.BaseReadSvc;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FeatureWriteSvcImpl extends AbstractBaseWriteSvc<FeatureDto, FeaturePo, FeatureQryDto, FeatureDao> implements FeatureWriteSvc {

    public FeatureWriteSvcImpl(FeatureDao baseDao, MapperFactory mapperFactory,
                               BaseReadSvc<FeatureDto, FeatureQryDto> baseReadSvc) {
        super(baseDao, mapperFactory, baseReadSvc);
    }
}
