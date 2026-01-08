package com.nie.tool.common.web.test;

import com.nie.tool.common.web.test.model.BaseForm;

/**
 * @author njy
 * @since 2026/1/8 22:13
 */
public interface IFormSectionHandler<T extends BaseForm> {
    String getSectionName();

    String save(T form);
}
