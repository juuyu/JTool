package com.nie.tool.common.web.test.service;

import com.nie.tool.common.web.test.COMSectionHandler;
import com.nie.tool.common.web.test.factory.ComSectionFactory;
import com.nie.tool.common.web.test.model.ComForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author njy
 * @since 2026/1/8 22:46
 */
@Component
@RequiredArgsConstructor
public class TestService {
    private ComSectionFactory comSectionFactory;


    public void saveComData(ComForm form, String key) {
//        if (isHK){
//
//        }
//        if (isSG){
//
//        }
//        if (isIn){
//
//        }
        COMSectionHandler processor = comSectionFactory.getProcessor(key);
        String save = processor.save(form);
    }
}
