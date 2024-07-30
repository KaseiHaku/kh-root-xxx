package com.kaseihaku.bpm.app.one.workflow.feature;

import com.kaseihaku.core.infra.constant.literal.ImmutableConstant;
import com.kaseihaku.core.ppe.constant.brand.NodeBrandConst;

public interface FeatureNodeBrand extends ImmutableConstant {
    public class V1 extends NodeBrandConst {

        protected V1(String key) {
            super(key);
        }

        public static final V1 base = new V1("base");
        public static final V1 extend = new V1("extend");
        public static final V1 branch1 = new V1("branch1");
        public static final V1 branch2 = new V1("branch2");
        public static final V1 counter_sign = new V1("counter_sign");
        public static final V1 join = new V1("join");
        public static final V1 overrule = new V1("overrule");
    }
}

