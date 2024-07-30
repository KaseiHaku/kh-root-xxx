package com.kaseihaku.bpm.app.one.repo.dao.feature;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kaseihaku.boot.starter.jwt.jws.JwsAccessor;
import com.kaseihaku.boot.starter.mybatis.plus.PlusPage;
import com.kaseihaku.bpm.app.one.ctrl.dto.FeatureQryDto;
import com.kaseihaku.cloud.starter.mybatis.pkg.arch.MpAbstractBaseDao;
import com.kaseihaku.bpm.app.one.repo.dao.mapper.FeatureMapper;
import com.kaseihaku.cloud.starter.feign.client.mvc.cloud.id.SnowFlakeIdFeign;
import com.kaseihaku.core.infra.pojo.Paged;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
class FeatureDaoImpl extends MpAbstractBaseDao<FeaturePo, FeatureQryDto, FeatureMapper> implements FeatureDao {

    public FeatureDaoImpl(SnowFlakeIdFeign cloudIdFeign, JwsAccessor jwsAccessor, FeatureMapper mapper) {
        super(cloudIdFeign, jwsAccessor, mapper);
    }

    @Override
    public FeaturePo create(FeaturePo po) {
        po.setCountSignTotal(0);  // 创建时，配默认值，也可在数据库层配，这里仅展示用法
        return super.create(po);
    }

    @Override
    public List<FeaturePo> query(Paged<FeatureQryDto> qryDto) {

        FeatureQryDto param = qryDto.getParam();


        IPage<FeaturePo> page = PlusPage.of(qryDto);
        if (Objects.isNull(param)) {
            IPage<FeaturePo> featurePoIPage = mapper.selectPage(page, null);
            return featurePoIPage.getRecords();
        }

        LambdaQueryWrapper<FeaturePo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(param.getId()), FeaturePo::getId, param.getId())
            .eq(Objects.nonNull(param.getPrivCreatorHgids()), FeaturePo::getPrivCreatorHgid, param.getPrivCreatorHgids())
            .eq(Objects.nonNull(param.getCreatorId()), FeaturePo::getCreatorId, param.getCreatorId())
            .ge(Objects.nonNull(param.getCreateTimeStart()), FeaturePo::getCreateTime, param.getCreateTimeStart())
            .lt(Objects.nonNull(param.getCreateTimeEnd()), FeaturePo::getCreateTime, param.getCreateTimeEnd())
            .eq(Objects.nonNull(param.getUpdaterId()), FeaturePo::getUpdaterId, param.getUpdaterId())
            .ge(Objects.nonNull(param.getUpdateTimeStart()), FeaturePo::getUpdateTime, param.getUpdateTimeStart())
            .lt(Objects.nonNull(param.getUpdateTimeEnd()), FeaturePo::getUpdateTime, param.getUpdateTimeEnd())
            .eq(Objects.nonNull(param.getVersion()), FeaturePo::getVersion, param.getVersion())
            /* 如果开启了 mybatis-plus 的逻辑删除功能，mybatis-plus 只能查询到 没有删除的数据，默认: 没开 */
            .eq(Objects.nonNull(param.getDeleted()), FeaturePo::getDeleted, param.getDeleted())
            // 以下为自定义字段
            .eq(Objects.nonNull(param.getProcId()), FeaturePo::getProcId, qryDto.getParam().getProcId())
            .eq(Objects.nonNull(param.getCountSignTotal()), FeaturePo::getCountSignTotal, qryDto.getParam().getCountSignTotal())
        ;

        return mapper.selectList(page, queryWrapper);
    }
}
