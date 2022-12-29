package com.project.auth.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permissions")
public class PermissionModel extends AbstractCompanyModel {

    @ManyToOne(optional = false)
    private EndpointModel endpointModel;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "p_order", nullable = false)
    private int order;

    @ManyToMany(mappedBy = "permissions")
    private List<GroupModel> groups;

    @Column(name = "p_menu", nullable = false,
            columnDefinition = "boolean default false")
    private boolean menu = false;

    @Column(name = "p_shortcut", nullable = false,
            columnDefinition = "boolean default false")
    private boolean shortcut = false;

    @Column(name = "p_delete", nullable = false,
            columnDefinition = "boolean default false")
    private boolean delete = false;

    @Column(name = "p_get", nullable = false,
            columnDefinition = "boolean default false")
    private boolean get = false;

    @Column(name = "p_post", nullable = false,
            columnDefinition = "boolean default false")
    private boolean post = false;

    @Column(name = "p_put", nullable = false,
            columnDefinition = "boolean default false")
    private boolean put = false;

    @Column(name = "p_patch", nullable = false,
            columnDefinition = "boolean default false")
    private boolean patch = false;

    @Column(name = "p_copy", nullable = false,
            columnDefinition = "boolean default false")
    private boolean copy = false;

    @Column(name = "p_head", nullable = false,
            columnDefinition = "boolean default false")
    private boolean head = false;

    public PermissionModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EndpointModel getEndpoint() {
        return endpointModel;
    }

    public void setEndpoint(EndpointModel endpointModel) {
        this.endpointModel = endpointModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public boolean isShortcut() {
        return shortcut;
    }

    public void setShortcut(boolean shortcut) {
        this.shortcut = shortcut;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public boolean isPost() {
        return post;
    }

    public void setPost(boolean post) {
        this.post = post;
    }

    public boolean isPut() {
        return put;
    }

    public void setPut(boolean put) {
        this.put = put;
    }

    public boolean isPatch() {
        return patch;
    }

    public void setPatch(boolean patch) {
        this.patch = patch;
    }

    public boolean isCopy() {
        return copy;
    }

    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    public static PermissionModel buildFull(EndpointModel endpoint, CompanyModel company,String name, String description, int order) {

        PermissionModel permission = new PermissionModel();
        permission.setEndpoint(endpoint);
        //permission.setCompany(company);
        permission.setName(name);
        permission.setDescription(description);
        permission.setMenu(true);
        permission.setGet(true);
        permission.setPost(true);
        permission.setPut(true);
        permission.setDelete(true);
        permission.setShortcut(true);
        permission.setOrder(order);

        return permission;
    }

    public static PermissionModel buildSend(EndpointModel endpoint, CompanyModel company,String name, String description, int order) {

        PermissionModel p = new PermissionModel();
        p.setEndpoint(endpoint);
        //p.setCompany(company);
        p.setName(name);
        p.setDescription(description);
        p.setMenu(false);
        p.setGet(false);
        p.setPost(true);
        p.setPut(false);
        p.setDelete(false);
        p.setShortcut(false);
        p.setOrder(order);

        return p;
    }

    public static PermissionModel buildList(EndpointModel endpoint, CompanyModel company,String name, String description, int order) {

        PermissionModel p = new PermissionModel();
        p.setEndpoint(endpoint);
        //p.setCompany(company);
        p.setName(name);
        p.setDescription(description);
        p.setMenu(false);
        p.setGet(true);
        p.setPost(false);
        p.setPut(false);
        p.setDelete(false);
        p.setShortcut(false);
        p.setOrder(order);

        return p;
    }

    public static PermissionModel buildView(EndpointModel endpoint, CompanyModel company,String name, String description, int order) {

        PermissionModel p = new PermissionModel();
        p.setEndpoint(endpoint);
        //p.setCompany(company);
        p.setName(name);
        p.setDescription(description);
        p.setMenu(true);
        p.setGet(true);
        p.setPost(false);
        p.setPut(false);
        p.setDelete(false);
        p.setShortcut(true);
        p.setOrder(order);

        return p;
    }
}
