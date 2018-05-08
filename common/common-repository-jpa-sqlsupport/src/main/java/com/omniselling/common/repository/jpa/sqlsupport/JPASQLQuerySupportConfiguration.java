package com.omniselling.common.repository.jpa.sqlsupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLTemplates;

@Configuration
public class JPASQLQuerySupportConfiguration
{
    @PersistenceContext
    private EntityManager em;

    @Bean(name = "sqlTemplates")
    public SQLTemplates createSQLTemplatesBean()
    {
        //TODO 待优化，可根据DatabaseMetaData动态获取
        return new MySQLTemplates();
    }

    @Bean(name = "jpaSqlQueryFactory")
    public JPASQLQueryFactory createQueryFactoryBean(@Qualifier(value = "sqlTemplates") SQLTemplates templates)
    {

        return new JPASQLQueryFactory(em, templates);
    }

}
