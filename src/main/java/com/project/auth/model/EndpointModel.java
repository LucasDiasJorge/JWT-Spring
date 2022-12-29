package com.project.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Endpoints", indexes = {
        @Index(columnList = "url, deleted_at", unique = true,
                name = "url_endpoint")})
public class EndpointModel extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "listed", nullable = false,
            columnDefinition = "boolean default true")
    private boolean listed = true;

    public EndpointModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public boolean isListed() {
        return listed;
    }

    public void setListed(boolean listed) {
        this.listed = listed;
    }

}
