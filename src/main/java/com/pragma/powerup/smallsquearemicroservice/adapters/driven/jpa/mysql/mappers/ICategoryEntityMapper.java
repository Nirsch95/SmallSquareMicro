package com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.smallsquearemicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.smallsquearemicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}
