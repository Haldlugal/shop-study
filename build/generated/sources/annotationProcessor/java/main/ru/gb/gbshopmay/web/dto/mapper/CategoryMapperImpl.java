package ru.gb.gbshopmay.web.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gb.gbapimay.category.dto.CategoryDto;
import ru.gb.gbapimay.category.dto.CategoryDto.CategoryDtoBuilder;
import ru.gb.gbshopmay.entity.Category;
import ru.gb.gbshopmay.entity.Category.CategoryBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T16:23:59+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.id( categoryDto.getId() );
        category.title( categoryDto.getTitle() );

        return category.build();
    }

    @Override
    public CategoryDto toCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.title( category.getTitle() );

        return categoryDto.build();
    }
}
