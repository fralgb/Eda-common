package com.omniselling.common.repository.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.omniselling.common.util.SpringContextUtils;

public class RepositoryFactory
{
    @SuppressWarnings("rawtypes")
    private static Map<String, CrudRepository> repositoryCache = new HashMap<>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> CrudRepository<T, Long> getRepository(Class clazz)
    {
        CrudRepository repo = repositoryCache.get(clazz.getName());
        if (repo != null)
        {
            return repo;
        }
        repo = (CrudRepository) SpringContextUtils.getBean("I" + clazz.getSimpleName() + "Repository",
                CrudRepository.class);
        if (repo == null)
        {
            return null;
        }
        repositoryCache.put(clazz.getName(), repo);
        return repo;
    }

}
