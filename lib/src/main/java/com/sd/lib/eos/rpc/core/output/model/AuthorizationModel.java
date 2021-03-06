package com.sd.lib.eos.rpc.core.output.model;

import com.sd.lib.eos.rpc.utils.Utils;

public class AuthorizationModel implements AuthorizationQuery
{
    private String actor;
    private String permission;

    public AuthorizationModel()
    {
    }

    public AuthorizationModel(String actor, String permission)
    {
        this.actor = actor;
        this.permission = permission;
    }

    public void check()
    {
        Utils.checkEmpty(this.actor, "AuthorizationModel.actor is empty");
        Utils.checkEmpty(this.permission, "AuthorizationModel.permission is empty");
    }

    public String getActor()
    {
        return actor;
    }

    public void setActor(String actor)
    {
        this.actor = actor;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    @Override
    public String queryActor()
    {
        return getActor();
    }

    @Override
    public String queryPermission()
    {
        return getPermission();
    }
}
