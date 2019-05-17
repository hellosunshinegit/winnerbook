package com.winnerbook.system.dto;

public class DefaultParamter {
    private Integer dpId;

    private String busAdminRoleId;

    private String busUserRoleName;

    private String busUserMenuIds;

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getBusAdminRoleId() {
        return busAdminRoleId;
    }

    public void setBusAdminRoleId(String busAdminRoleId) {
        this.busAdminRoleId = busAdminRoleId == null ? null : busAdminRoleId.trim();
    }

    public String getBusUserRoleName() {
        return busUserRoleName;
    }

    public void setBusUserRoleName(String busUserRoleName) {
        this.busUserRoleName = busUserRoleName == null ? null : busUserRoleName.trim();
    }

    public String getBusUserMenuIds() {
        return busUserMenuIds;
    }

    public void setBusUserMenuIds(String busUserMenuIds) {
        this.busUserMenuIds = busUserMenuIds == null ? null : busUserMenuIds.trim();
    }
}