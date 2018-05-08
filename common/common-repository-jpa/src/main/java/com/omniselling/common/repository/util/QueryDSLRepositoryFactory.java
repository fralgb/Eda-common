package com.omniselling.common.repository.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.omniselling.common.util.SpringContextUtils;

public class QueryDSLRepositoryFactory
{
    @SuppressWarnings("rawtypes")
    private static Map<String, QueryDslPredicateExecutor> repositoryCache = new HashMap<>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> QueryDslPredicateExecutor<T> getRepository(Class clazz)
    {
        QueryDslPredicateExecutor repo = repositoryCache.get(clazz.getName());
        if (repo != null)
        {
            return repo;
        }
        repo = (QueryDslPredicateExecutor) SpringContextUtils.getBean("I" + clazz.getSimpleName() + "Repository",
                QueryDslPredicateExecutor.class);
        if (repo == null)
        {
            return null;
        }
        repositoryCache.put(clazz.getName(), repo);
        return repo;
    }

}
