package com.nie.tool.common.web.test.base;

import com.nie.tool.common.web.test.COMSectionHandler;
import com.nie.tool.common.web.test.model.ComForm;

/**
 * @author njy
 * @since 2026/1/8 22:17
 */
public abstract class ComSectionProcessor extends BaseFormSectionProcessor<ComForm> implements COMSectionHandler {
    @Override
    public String save(ComForm form) {
        return process(form).toString();
    }
}
