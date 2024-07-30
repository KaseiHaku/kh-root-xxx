package com.kaseihaku.bpm.app.one.repo.dao.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kaseihaku.cloud.starter.mybatis.pkg.arch.MpAbstractBaseDao;
import com.kaseihaku.core.infra.constant.cls.DeletedFlagConstant;
import com.kaseihaku.core.infra.validate.group.DaoDeleteVg;
import com.kaseihaku.core.infra.validate.group.DaoUpdateVg;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * 功能完全和 {@link com.kaseihaku.core.infra.pojo.BasePo} 相同，
 * 只是由于 mybatis-plus 没办法对不在当前类的字段配置 {@link TableId} {@link TableLogic} 等注解，
 * 所以单独建一个类使用
 *
 * @trap 如果使用该类则无法使用 {@link MpAbstractBaseDao} 的子类
 * */
@Data
public class MpBasePo implements Serializable {

    @TableId
    @NotNull(message = "id can not be null", groups = {DaoUpdateVg.class, DaoDeleteVg.class})
    private Long id;

    private Long privCreatorHgid;

    private Long creatorId;

    private Instant createTime;

    private Long updaterId;

    private Instant updateTime;

    @NotNull(message = "version cant not be null",  groups = {DaoUpdateVg.class})
    private Integer version;

    @TableLogic
    private DeletedFlagConstant deleted;
}
