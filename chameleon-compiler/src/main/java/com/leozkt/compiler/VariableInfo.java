package com.leozkt.compiler;

import javax.lang.model.element.VariableElement;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
public class VariableInfo {
    int layoutId;
    Class entityClass;
    VariableElement mVariableElement;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public void setVariableElement(VariableElement variableElement) {
        mVariableElement = variableElement;
    }
}
