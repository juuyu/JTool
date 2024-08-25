package com.nie.tool.common.core.model;

import com.nie.tool.common.core.enums.DeviceType;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author njy
 * @since 2024/8/15 14:07
 */
@Data
public class LoginUser implements Serializable {

    /**
     * 用户ID
     */
    private String      userId;
    /**
     * 用户名
     */
    private String      username;
    /**
     * 租户id
     */
    private String      tenantId;
    /**
     * 部门ID
     */
    private String      deptId;
    /**
     * token
     */
    private String      token;
    /**
     * 登录设备类型
     */
    private DeviceType  deviceType;
    /**
     * 菜单权限
     */
    private Set<String> menuPermission;
    /**
     * 角色权限
     */
    private Set<String> rolePermission;
}
