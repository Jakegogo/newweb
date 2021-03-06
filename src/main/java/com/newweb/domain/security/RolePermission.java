package com.newweb.domain.security;

import com.newweb.common.domain.IEntity;

/**
 *  
 */
public class RolePermission extends RolePermissionKey implements IEntity {
    /**
     *  角色权限描述
     */
    private String rolePermissionDesc;

    private static final long serialVersionUID = 1L;

    /**
     * 获取字段 system_role_permission.role_permission_desc
     *
     * @return the value of system_role_permission.role_permission_desc
     */
    public String getRolePermissionDesc() {
        return rolePermissionDesc;
    }

    /**
     * 设置字段 system_role_permission.role_permission_desc
     *
     * @param rolePermissionDesc the value for system_role_permission.role_permission_desc
     */
    public void setRolePermissionDesc(String rolePermissionDesc) {
        this.rolePermissionDesc = rolePermissionDesc == null ? null : rolePermissionDesc.trim();
    }

    public RolePermissionKey getId() {
        RolePermissionKey key = new RolePermissionKey();
		key.setRoleId(this.getRoleId());
		key.setPermissionId(this.getPermissionId());
		return key;
    }
}