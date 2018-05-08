package com.omniselling.common.repository.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.omniselling.common.util.SpringContextUtils;

public class ExampleQueryRepositoryFactory
{
    @SuppressWarnings("rawtypes")
    private static Map<String, QueryByExampleExecutor> repositoryCache = new HashMap<>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> QueryByExampleExecutor<T> getRepository(Class clazz)
    {
        QueryByExampleExecutor repo = repositoryCache.get(clazz.getName());
        if (repo != null)
        {
            return repo;
        }
        repo = (QueryByExampleExecutor) SpringContextUtils.getBean("I" + clazz.getSimpleName() + "Repository",
                QueryByExampleExecutor.class);
        if (repo == null)
        {
            return null;
        }
        repositoryCache.put(clazz.getName(), repo);
        return repo;
    }

}
