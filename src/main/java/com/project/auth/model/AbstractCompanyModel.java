package com.project.auth.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCompanyModel extends AbstractModel {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyModel company;

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

}