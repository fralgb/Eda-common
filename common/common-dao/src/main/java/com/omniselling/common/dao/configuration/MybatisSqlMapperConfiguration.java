package com.omniselling.common.dao.configuration;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Bean;

import com.omniselling.common.dao.SqlMapper;


public class MybatisSqlMapperConfiguration
{

	@Bean
	SqlMapper SqlMapper(SqlSession sqlSession){
		return new SqlMapper(sqlSession);
	}
	
}
