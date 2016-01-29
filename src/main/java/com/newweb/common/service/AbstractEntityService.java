package com.newweb.common.service;

import com.newweb.common.assembler.AbstractAssembler;
import com.newweb.common.domain.IEntity;
import com.newweb.common.persist.IEntityMapper;

import java.io.Serializable;

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

    private AbstractAssembler<D, E> entityAssembler = new AbstractAssembler<D, E>(){};

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
     * @param e 实体
     */
    public void save(E e) {
        IEntityMapper<E, PK> entityMapper = getEntityMapper();
        entityMapper.save(e);
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