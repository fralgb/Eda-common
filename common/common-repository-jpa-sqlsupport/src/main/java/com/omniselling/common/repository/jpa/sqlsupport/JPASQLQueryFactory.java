package com.omniselling.common.repository.jpa.sqlsupport;

import javax.persistence.EntityManager;

import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;

public class JPASQLQueryFactory
{
    private EntityManager em;

    private SQLTemplates template;

    public JPASQLQueryFactory(EntityManager em, SQLTemplates template)
    {
        this.em = em;
        this.template = template;
    }

    public <T> JPASQLQuery<T> newQuery()
    {

        return new JPASQLQuery<>(em, template);
    }

}
