package com.nie.tool.common.flowable.common.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.nie.tool.common.flowable.common.dao.entity.FlowableCroupRelationDO;
import com.nie.tool.common.flowable.common.dao.mapper.FlowableGroupRelationMapper;
import com.nie.tool.common.flowable.common.manager.FlowableGroupRelationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/3 10:10
 */
@Service
@RequiredArgsConstructor
public class FlowableGroupRelationManagerImpl implements FlowableGroupRelationManager {
    private final FlowableGroupRelationMapper flowableGroupRelationMapper;

    @Override
    public Map<String, List<String>> listAllGroupRelations(String tenantId) {
//        List<FlowableCroupRelationDO> relationDOS = flowableGroupRelationMapper.selectList(new LambdaQueryWrapper<FlowableCroupRelationDO>()
//                .eq(FlowableCroupRelationDO::getTenantId, tenantId));
        // TODO replace mybatis-flex
        List<FlowableCroupRelationDO> relationDOS = new ArrayList<>();

        Map<String, List<String>> res = new HashMap<>();
        if (CollectionUtil.isNotEmpty(relationDOS)) {
            Map<String, List<FlowableCroupRelationDO>> collect = relationDOS.stream()
                    .collect(Collectors.groupingBy(FlowableCroupRelationDO::getGroupKey));
            for (Map.Entry<String, List<FlowableCroupRelationDO>> entry : collect.entrySet()) {
                List<String> identityIds = entry.getValue().stream().sorted(Comparator.comparing(FlowableCroupRelationDO::getOrder))
                        .map(FlowableCroupRelationDO::getIdentityId)
                        .collect(Collectors.toList());
                res.put(entry.getKey(), identityIds);
            }
        }
        return res;
    }

    @Override
    public List<String> listGroupKeyByIdentity(String tenantId, String identityId) {
//        List<FlowableCroupRelationDO> relationDOS = flowableGroupRelationMapper.selectList(new LambdaQueryWrapper<FlowableCroupRelationDO>()
//                .eq(FlowableCroupRelationDO::getTenantId, tenantId)
//                .eq(FlowableCroupRelationDO::getIdentityId, identityId));
        // TODO replace mybatis-flex
        List<FlowableCroupRelationDO> relationDOS = new ArrayList<>();
        List<String> res = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(relationDOS)) {
            res = relationDOS.stream().map(FlowableCroupRelationDO::getGroupKey)
                    .collect(Collectors.toList());
        }
        return res;
    }

    @Override
    public List<String> listIdentityIdByGroupKey(String tenantId, String groupKey) {
//        List<FlowableCroupRelationDO> relationDOS = flowableGroupRelationMapper.selectList(new LambdaQueryWrapper<FlowableCroupRelationDO>()
//                .eq(FlowableCroupRelationDO::getGroupKey, groupKey)
//                .eq(FlowableCroupRelationDO::getTenantId, tenantId)
//                .orderByAsc(FlowableCroupRelationDO::getOrder));

        // TODO replace mybatis-flex
        List<FlowableCroupRelationDO> relationDOS = new ArrayList<>();
        List<String> res = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(relationDOS)) {
            res = relationDOS.stream().map(FlowableCroupRelationDO::getIdentityId)
                    .collect(Collectors.toList());
        }
        return res;
    }

    @Override
    public void saveGroupRelation(List<FlowableCroupRelationDO> flowableCroupRelationDOS) {
        for (FlowableCroupRelationDO relationDO : flowableCroupRelationDOS) {
            // TODO replace mybatis-flex

//            flowableGroupRelationMapper.insert(relationDO);
        }
    }

    @Override
    public void deleteGroupRelationByKey(String tenantId, String groupKey) {
        // TODO replace mybatis-flex

//        flowableGroupRelationMapper.delete(new LambdaQueryWrapper<FlowableCroupRelationDO>()
//                .eq(FlowableCroupRelationDO::getTenantId, tenantId)
//                .eq(FlowableCroupRelationDO::getGroupKey, groupKey));
    }
}
