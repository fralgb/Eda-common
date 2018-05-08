package com.omniselling.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Atomic
 *
 * @param T
 *            should be either Long or String type
 */
@MappedSuperclass
public abstract class BizBaseModel<T> implements Serializable
{

    private static final long serialVersionUID = 1L;

    //@Column(length = 1024)
    protected @Getter @Setter String memo;
    
    @Column
    protected @Getter @Setter Long createdBy;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected @Getter @Setter Date createdDate;
    
    @Column
    protected @Getter @Setter Long modifiedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected @Getter @Setter Date modifiedDate;

    public abstract T getId();

    public abstract void setId(T id);

}
