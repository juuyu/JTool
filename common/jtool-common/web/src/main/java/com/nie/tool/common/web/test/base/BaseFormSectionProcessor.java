package com.nie.tool.common.web.test.base;

import com.nie.tool.common.web.test.model.BaseForm;

import java.util.List;

/**
 * @author njy
 * @since 2026/1/8 22:13
 */
public abstract class BaseFormSectionProcessor<T extends BaseForm> {

    protected T preProcess(T form) {
        return form;
    }

    protected abstract List<String> validate(T form);

    protected void postProcess(Object result) {
    }

    public Object process(T form) {
        form = preProcess(form);
        validate(form);
        Object result = save(form);
        postProcess(result);
        return result;
    }

    private Object save(T form) {
        return new Object();
    }
}
