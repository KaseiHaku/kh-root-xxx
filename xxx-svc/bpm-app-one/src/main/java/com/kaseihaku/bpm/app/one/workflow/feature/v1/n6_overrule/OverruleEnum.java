package com.kaseihaku.bpm.app.one.workflow.feature.v1.n6_overrule;

import com.kaseihaku.core.infra.constant.AbstractDescClassConstant;

public class OverruleEnum extends AbstractDescClassConstant {

    public OverruleEnum(String key) {
        this(key, null);
    }

    protected OverruleEnum(String key, String description) {
        super(key, description);
    }


    public static final OverruleEnum yes = new OverruleEnum("yes", "是");
    public static final OverruleEnum no = new OverruleEnum("no", "否");
}
