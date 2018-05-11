package com.leozkt.compiler;

import javax.lang.model.element.VariableElement;

/**
 * @author zhengkaituo
 */
public class VariableInfo {
    int layoutId;
    String entityClass;
    VariableElement mVariableElement;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public void setVariableElement(VariableElement variableElement) {
        mVariableElement = variableElement;
    }
}
