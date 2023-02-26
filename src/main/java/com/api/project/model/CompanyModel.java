package com.api.project.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TB_COMPANIES", indexes = {
        @Index(columnList = "cnpj, deleted_at", unique = true,
                name = "cnpj_companies")})
public class CompanyModel extends AbstractModel {

    @Column(name = "client_key")
    private String clientKey;

    @Column(name = "fancy_name", nullable = false)
    private String fancyName;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "epc_prefix", length = 16)
    private String epcPrefix;

    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "moving_item_creation", nullable = false)
    private int movingItemCreation = 0;

    public CompanyModel(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getFancyName() {
        return fancyName;
    }

    public void setFancyName(String fancyName) {
        this.fancyName = fancyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getEpcPrefix() {
        return epcPrefix;
    }

    public void setEpcPrefix(String epcPrefix) {
        this.epcPrefix = epcPrefix;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getMovingItemCreation() {
        return movingItemCreation;
    }

    public void setMovingItemCreation(int movingItemCreation) {
        this.movingItemCreation = movingItemCreation;
    }

}