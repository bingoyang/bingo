package com.visfull.system.domain;


import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AuthResource generated by hbm2java
 */
@Entity
@Table(name = "auth_resource")
public class AuthResource implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private long id;
    private String resourceName;
    private String resourceType;
    private String resourceDescribe;
    private Long resourcePid;
    private String targetUrl;
    private Date createDate;
    private Set<UserRole> roles;
    
    public AuthResource() {
    }

    public AuthResource(long id) {
        this.id = id;
    }

    public AuthResource(long id, String resourceName, String resourceType, String resourceDescribe, Long resourcePid,
            String targetUrl, Date createDate) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resourceDescribe = resourceDescribe;
        this.resourcePid = resourcePid;
        this.targetUrl = targetUrl;
        this.createDate = createDate;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "resource_name", length = 64)
    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(name = "resource_type", length = 32)
    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "resource_describe")
    public String getResourceDescribe() {
        return this.resourceDescribe;
    }

    public void setResourceDescribe(String resourceDescribe) {
        this.resourceDescribe = resourceDescribe;
    }

    @Column(name = "resource_pid")
    public Long getResourcePid() {
        return this.resourcePid;
    }

    public void setResourcePid(Long resourcePid) {
        this.resourcePid = resourcePid;
    }

    @Column(name = "target_url")
    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "auth_role_map", catalog = "bingo", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
    
}