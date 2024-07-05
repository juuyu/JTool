package com.nie.tool.common.flowable.common.manager;



import com.nie.tool.common.flowable.common.dao.entity.FlowableCroupRelationDO;

import java.util.List;
import java.util.Map;

/**
 * @author njy
 * @since 2024/7/1 16:03
 */
public interface FlowableGroupRelationManager {


    /**
     * listAllGroupRelations(k: groupKey, v: identityIds)
     *
     * @param tenantId tenantId
     * @return java.util.Map<java.lang.String, java.util.List < java.lang.String>>
     */
    Map<String, List<String>> listAllGroupRelations(String tenantId);

    /**
     * listGroupKeyByIdentity
     *
     * @param tenantId   tenantId
     * @param identityId identityId
     * @return java.util.List<java.lang.String>
     */
    List<String> listGroupKeyByIdentity(String tenantId, String identityId);


    /**
     * listIdentityIdByGroupKey
     *
     * @param tenantId tenantId
     * @param groupKey groupKey
     * @return java.util.List<java.lang.String>
     */
    List<String> listIdentityIdByGroupKey(String tenantId, String groupKey);

    /**
     * saveGroupRelation
     *
     * @param flowableCroupRelationDOS flowableCroupRelationDOS
     */
    void saveGroupRelation(List<FlowableCroupRelationDO> flowableCroupRelationDOS);

    /**
     * deleteGroupRelationByKey
     *
     * @param tenantId tenantId
     * @param groupKey groupKey
     */
    void deleteGroupRelationByKey(String tenantId, String groupKey);
}
