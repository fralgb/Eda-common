package com.omniselling.common.repository.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;

public class EntityCrudUtil
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends BizBaseModel> T save(T data)
    {
        if (data == null)
        {
            return null;
        }
        CrudRepository repo = RepositoryFactory.getRepository(data.getClass());
        if (repo == null)
        {
            throw new NullPointerException("Can't find repository for " + data.getClass());
        }
        return (T) repo.save(data);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T getById(Class<T> clazz, Serializable id)
    {
        CrudRepository repo = RepositoryFactory.getRepository(clazz);
        if (repo == null)
        {
            throw new NullPointerException("Can't find repository for " + clazz);
        }
        return (T) repo.findOne(id);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> void deleteById(Class<T> clazz, Serializable id)
    {
        CrudRepository repo = RepositoryFactory.getRepository(clazz);
        if (repo == null)
        {
            throw new NullPointerException("Can't find repository for " + clazz);
        }
        repo.delete(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> void deleteByEntity(T data)
    {
        if (data == null)
        {
            return;
        }
        CrudRepository repo = RepositoryFactory.getRepository(data.getClass());
        if (repo == null)
        {
            throw new NullPointerException("Can't find repository for " + data.getClass());
        }
        repo.delete(data);
    }
}
