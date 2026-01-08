package com.nie.tool.common.web.test.factory;

import com.nie.tool.common.web.test.COMSectionHandler;
import com.nie.tool.common.web.test.base.BaseSectionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njy
 * @since 2026/1/8 22:40
 */
@Component
public class ComSectionFactory extends BaseSectionFactory<COMSectionHandler> {
    public ComSectionFactory(List<COMSectionHandler> comSectionHandlers) {
        super(comSectionHandlers);
    }
}
