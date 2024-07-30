package com.kaseihaku.bpm.app.one.workflow.feature.v1.n3_1_branch;

import com.kaseihaku.bpm.starter.ppe.render.engine.pojo.anno.PpeFormField;
import com.kaseihaku.core.ppe.node.meta.PpeNodeMetaDto;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * 该类是公共 POJO {@link com.kaseihaku.bpm.starter.ppe.render.engine.naked.view.AgreePojo} 的 copy
 * 放在这里是为了演示如何对 POJO 进行自定义校验
 * 同时配置 {@link PpeNodeMetaDto#renderEngineParams} 的 viewPath，
 * 用于演示 NativeRe 强制使用指定路径的组件进行渲染，适用于 POJO 和 渲染组件 路径不一致的场景
 *
 * @tips 可以直接将当前 Node 的 POJO 配置成 公共的 POJO {@link com.kaseihaku.bpm.starter.ppe.render.engine.naked.view.AgreePojo}
 *       这样 渲染引擎参数 就可以不用配，
 *       后端也不需要定义 专用的 POJO，这样可以显著减少 POJO 类的数量,
 *       例如:
 *          节点 {@link com.kaseihaku.bpm.app.one.workflow.feature.v1.n3_2_branch} 就没有定义 专用的 POJO，
 *          而是在 NodeMeta 配置时，直接使用 公共 POJO
 */
@Data
@MyAgreePojo.AgreeOrDisagreePojoConstraint(message = "自定义 是否同意 POJO 校验出错")
public class MyAgreePojo {
    @NotNull
    @PpeFormField(label = "是否同意")
    private Boolean agree;
    @PpeFormField(label = "备注")
    private String remark;


    @Target({ TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy= {AgreeOrDisagreePojoConstraint.ValidateBy.class})
    public static @interface AgreeOrDisagreePojoConstraint {


        String message() default "{com.kaseihaku.bpm.starter.ppe.render.engine.naked.view.AgreeOrDisagreePojo.AgreeOrDisagreePojoConstraint.message}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};


        public class ValidateBy implements ConstraintValidator<AgreeOrDisagreePojoConstraint, MyAgreePojo> {


            @Override
            public void initialize(AgreeOrDisagreePojoConstraint constraintAnnotation) {

            }
            @Override
            public boolean isValid(MyAgreePojo value, ConstraintValidatorContext context) {
                if(value.agree){
                    return true;
                }
                if (StringUtils.isBlank(value.remark)) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("不同意时，备注必填")
                        .addConstraintViolation();
                    return false;
                }


                return true;
            }
        }

    }
}
