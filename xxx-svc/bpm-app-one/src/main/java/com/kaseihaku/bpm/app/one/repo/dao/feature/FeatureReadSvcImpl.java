package com.kaseihaku.bpm.app.one.repo.dao.feature;

import com.kaseihaku.boot.starter.orika.pkg.arch.AbstractBaseReadSvc;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureDto;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQry;
import com.kaseihaku.bpm.app.one.svc.FeatureReadSvc;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FeatureReadSvcImpl extends AbstractBaseReadSvc<FeatureDto, FeaturePo, FeatureQry, FeatureDao> implements FeatureReadSvc {

    public FeatureReadSvcImpl(FeatureDao baseDao, MapperFactory mapperFactory) {
        super(baseDao, mapperFactory);
    }
}
