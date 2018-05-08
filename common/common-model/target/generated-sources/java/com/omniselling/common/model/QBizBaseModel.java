package com.omniselling.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBizBaseModel is a Querydsl query type for BizBaseModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBizBaseModel extends EntityPathBase<BizBaseModel<?>> {

    private static final long serialVersionUID = -933320215L;

    public static final QBizBaseModel bizBaseModel = new QBizBaseModel("bizBaseModel");

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> modifiedBy = createNumber("modifiedBy", Long.class);

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBaseModel(String variable) {
        super((Class) BizBaseModel.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBaseModel(Path<? extends BizBaseModel> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBaseModel(PathMetadata metadata) {
        super((Class) BizBaseModel.class, metadata);
    }

}

