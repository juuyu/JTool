package com.nie.tool.common.web.test.base;

import com.nie.tool.common.web.test.IFormSectionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author njy
 * @since 2026/1/8 22:38
 */
public abstract class BaseSectionFactory<T extends IFormSectionHandler> {
    protected final Map<String, T> handlerMap;

    public BaseSectionFactory(List<T> iFormSectionHandlers) {
        handlerMap = new HashMap<>();
        for (T iFormSectionHandler : iFormSectionHandlers) {
            handlerMap.put(iFormSectionHandler.getSectionName(), iFormSectionHandler);
        }
    }

    public T getProcessor(String sectionName) {
        return handlerMap.get(sectionName);
    }
}
