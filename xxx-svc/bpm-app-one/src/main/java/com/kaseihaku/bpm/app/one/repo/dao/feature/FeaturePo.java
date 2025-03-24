package com.kaseihaku.bpm.app.one.repo.dao.feature;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kaseihaku.boot.starter.mybatis.typehandler.ClassConstantTypeHandler;
import com.kaseihaku.boot.starter.orika.pkg.arch.AbstractBaseReadSvc;
import com.kaseihaku.boot.starter.orika.pkg.arch.AbstractBaseWriteSvc;
import com.kaseihaku.bpm.app.one.repo.dao.po.MpBasePo;
import com.kaseihaku.cloud.starter.mybatis.pkg.arch.MpAbstractBaseDao;
import com.kaseihaku.core.infra.pojo.BasePo;
import com.kaseihaku.core.infra.validate.group.DaoCreateVg;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.type.TypeHandler;

/**
 *
 * @trap mybatis-plus 逻辑删除字段如果存在于 PO 里面，会有警告
 *       如果不想有 warning 只能自己定义全部字段 或 继承 {@link MpBasePo}
 *       但是这样将无法使用
 *       {@link MpAbstractBaseDao} {@link AbstractBaseReadSvc} {@link AbstractBaseWriteSvc}
 *       简化配置
 *
 *
 * @trap 由于 Mybatis {@link TypeHandler} 的 context 中不包含 property/field 的具体类型，
 *       所以无法自动处理 {@link com.kaseihaku.core.infra.constant.ClassConstant} 类型的字段，
 *       解决方案一:
 *          给具体的类型定义独立的 {@link TypeHandler}
 *          直接继承 {@link ClassConstantTypeHandler} ，把泛型参数修改为具体类型，并将该 {@link TypeHandler} 配置到 Mybatis 中即可
 *       解决方案二:
 *          DTO 中使用 XxxClassConstant，PO 中使用 string 替代，
 *          如果 XxxSvcImpl 是继承 {@link AbstractBaseReadSvc} 或 {@link AbstractBaseWriteSvc}
 *          那么会使用 Orika 自动进行类型转换
 *
 *       推荐方案二，因为不用定义一大堆子类的 {@link TypeHandler}
 * */
@Data
@TableName(value = "ppe_feature")
public class FeaturePo extends BasePo {

    @NotNull(message = "流程 id 不能为空", groups = {DaoCreateVg.class})
    private Long procId; // 流程引擎中的流程 ID
    private Integer countSignTotal;



}
