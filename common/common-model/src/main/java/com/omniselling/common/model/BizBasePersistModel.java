package com.omniselling.common.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

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
public abstract class BizBasePersistModel<T extends Serializable> extends BizBaseModel<T> implements Serializable,Persistable<T>
{

    private static final long serialVersionUID = -3390692204792110841L;
    
    @Transient
    private @Getter @Setter boolean isNew;


}
