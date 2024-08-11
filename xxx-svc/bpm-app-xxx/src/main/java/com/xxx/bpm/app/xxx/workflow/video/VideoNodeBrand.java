package com.xxx.bpm.app.xxx.workflow.video;

import com.kaseihaku.core.infra.constant.literal.ImmutableConstant;
import com.kaseihaku.core.ppe.constant.brand.NodeBrandConst;

public interface VideoNodeBrand extends ImmutableConstant {
    public class V1 extends NodeBrandConst {

        protected V1(String key) {
            super(key);
        }

        public static final V1 first = new V1("first");
    }
}

