package com.storage.service.impl;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import javax.annotation.PostConstruct;

public abstract class AbstractMapperService<E, DTO> {

    private ModelMapper entityMapper = initiateModelMapper();
    private ModelMapper dtoMapper = initiateModelMapper();

    public AbstractMapperService() {
    }

    @PostConstruct
    public void initMap() {
        initConfig(entityMapper, dtoMapper);
    }


    protected void initConfig(ModelMapper entityMapper, ModelMapper dtoMapper) {
        entityMapper.getConfiguration().setPropertyCondition(new Condition<E, DTO>() {
            @Override
            public boolean applies(MappingContext<E, DTO> context) {
                return context.getSource() != null;
            }
        });
    }

    public ModelMapper initiateModelMapper() {
        return new ModelMapper();
    }

    protected DTO toDTO(E entity) {
        return entity != null ? dtoMapper.map(entity, getDTOClass()) : null;
    }

    protected E toEntity(DTO dto) {
        return dto != null ? entityMapper.map(dto, getEntityClass()) : null;
    }

    protected abstract Class<DTO> getDTOClass();

    protected abstract Class<E> getEntityClass();


}
