package com.kaseihaku.bpm.app.one.workflow;

import com.kaseihaku.core.ppe.constant.brand.ProcBrandConst;

public class BpmAppOneProcBrand extends ProcBrandConst {


    protected BpmAppOneProcBrand(String key) {
        super(key);
    }

    public static final BpmAppOneProcBrand feature = new BpmAppOneProcBrand("feature");
}
