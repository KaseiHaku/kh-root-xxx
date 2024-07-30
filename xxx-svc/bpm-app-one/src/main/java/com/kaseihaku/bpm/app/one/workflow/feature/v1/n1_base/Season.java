package com.kaseihaku.bpm.app.one.workflow.feature.v1.n1_base;

import com.kaseihaku.core.infra.constant.AbstractDescClassConstant;

public class Season extends AbstractDescClassConstant {

    public Season(String key) {
        this(key, null);
    }

    protected Season(String key, String description) {
        super(key, description);
    }




    public static class V1 extends Season {
        public V1(String key) {
            this(key, null);
        }

        protected V1(String key, String description) {
            super(key, description);
        }

        public static final V1 spring = new V1("spring", "春");
        public static final V1 summer = new V1("summer", "夏");
        public static final V1 autumn = new V1("autumn", "秋");
        public static final V1 winter = new V1("winter", "冬");
    }


    public static class V2 extends Season {
        public V2(String key) {
            this(key, null);
        }

        protected V2(String key, String description) {
            super(key, description);
        }

        public static final V2 spring = new V2("spring", "春");
        public static final V2 summer = new V2("summer", "夏");
        public static final V2 autumn = new V2("autumn", "秋");
    }



}
