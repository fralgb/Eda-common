package com.omniselling.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBizBasePersistModel is a Querydsl query type for BizBasePersistModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBizBasePersistModel extends EntityPathBase<BizBasePersistModel<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -125333771L;

    public static final QBizBasePersistModel bizBasePersistModel = new QBizBasePersistModel("bizBasePersistModel");

    public final QBizBaseModel _super = new QBizBaseModel(this);

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath memo = _super.memo;

    //inherited
    public final NumberPath<Long> modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBasePersistModel(String variable) {
        super((Class) BizBasePersistModel.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBasePersistModel(Path<? extends BizBasePersistModel> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBizBasePersistModel(PathMetadata metadata) {
        super((Class) BizBasePersistModel.class, metadata);
    }

}

