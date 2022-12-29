package com.project.auth.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Groups", indexes = {
        @Index(columnList = "company_id, deleted_at", unique = true,
                name = "name_Groups")})
public class GroupModel extends AbstractModel {

    private String name;

    @ManyToMany
    @JoinTable(name = "Users_Groups",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserModel> users;

    @ManyToMany
    @JoinTable(name = "Groups_Permissions",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<PermissionModel> permissions;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel companyModel;

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    public GroupModel() {
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<PermissionModel> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }
}
