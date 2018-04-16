package com.greentown.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

/**
 * @author zhengkaituo
 * @date 2018/4/9
 */
@SupportedAnnotationTypes("com.greentown.compile.CommonSingleType")
public class AutoLayoutSingleProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
