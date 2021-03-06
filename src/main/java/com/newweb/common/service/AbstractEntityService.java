package com.newweb.common.service;

import com.newweb.common.assembler.AbstractAssembler;
import com.newweb.common.domain.IEntity;
import com.newweb.common.persist.IEntityMapper;
import com.newweb.common.util.GenericsUtils;
import com.newweb.common.util.PageInfo;
import com.newweb.common.util.PageResult;
import com.newweb.common.util.QueryParameters;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 通用抽象实体Service
 * Created by Jake on 1/18 0018.
 */
public abstract class AbstractEntityService<D, E extends IEntity, PK extends Serializable> {

    /**
     * 获取实体mapper对象
     * @return
     */
    protected abstract IEntityMapper<E, PK> getEntityMapper();

    private AbstractAssembler<D, E> entityAssembler = new AbstractAssembler<D, E>(
            GenericsUtils.getSuperClassGenricType(this.getClass(), 0),
            GenericsUtils.getSuperClassGenricType(this.getClass(), 1)){};


    /**
     * 分页查询
     * @param queryParam 查询参数
     * @return
     */
    public PageResult<D> page(QueryParameters queryParam) {
        IEntityMapper<E, PK> entityMapper = getEntityMapper();
        PageInfo pageInfo = queryParam.getPage();

        int count = entityMapper.count(queryParam);
        if (count == 0) {
            return PageResult.valueOf(pageInfo.resetTotal(0),
                    (List<D>) Collections.emptyList());
        }

        pageInfo.resetTotal(count);

        List<E> entities = entityMapper.page(queryParam);
        if (entities == null || entities.isEmpty()) {
            return PageResult.valueOf(pageInfo,
                    (List<D>) Collections.emptyList());
        }

        Collection<D> dtos = entityAssembler.toDTOs(entities);
        return PageResult.valueOf(pageInfo, dtos);
    }

    /**
     * 获取实体Dto
     * @param id 实体主键
     * @return
     */
    public D get(PK id) {
        IEntityMapper<E, PK> entityMapper = getEntityMapper();
        E entity = entityMapper.get(id);
        if (entity == null) {
            return null;
        }
        return entityAssembler.toDTO(entity);
    }


    /**
     * 保存实体
     * @param dto 实体Dto
     */
    public void save(D dto) {
        IEntityMapper<E, PK> entityMapper = getEntityMapper();
        E entity = entityAssembler.toEntity(dto);
        if (entity == null) {
            return;
        }
        entityMapper.save(entity);
    }


    /**
     * 删除实体
     * @param id 实体主键
     * @return
     */
    public int remove(PK id) {
        IEntityMapper<E, PK> entityMapper = getEntityMapper();
        return entityMapper.delete(id);
    }




}
