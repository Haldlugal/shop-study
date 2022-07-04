package ru.gb.gbshopmay.web.dto.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gb.gbapimay.category.dto.CategoryDto;
import ru.gb.gbapimay.category.dto.CategoryDto.CategoryDtoBuilder;
import ru.gb.gbapimay.product.dto.ProductDto;
import ru.gb.gbapimay.product.dto.ProductDto.ProductDtoBuilder;
import ru.gb.gbshopmay.dao.CategoryDao;
import ru.gb.gbshopmay.dao.ManufacturerDao;
import ru.gb.gbshopmay.entity.Category;
import ru.gb.gbshopmay.entity.Product;
import ru.gb.gbshopmay.entity.Product.ProductBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T16:23:59+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDto productDto, ManufacturerDao manufacturerDao, CategoryDao categoryDao) {
        if ( productDto == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        product.id( productDto.getId() );
        product.title( productDto.getTitle() );
        product.cost( productDto.getCost() );
        product.manufactureDate( productDto.getManufactureDate() );
        product.manufacturer( getManufacturer( productDto.getManufacturer(), manufacturerDao ) );
        product.status( productDto.getStatus() );
        product.categories( categoryDtoSetToCategorySet( productDto.getCategories(), categoryDao ) );

        return product.build();
    }

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.title( product.getTitle() );
        productDto.cost( product.getCost() );
        productDto.oldCost( product.getOldCost() );
        productDto.manufactureDate( product.getManufactureDate() );
        productDto.status( product.getStatus() );
        productDto.manufacturer( getManufacturer( product.getManufacturer() ) );
        productDto.categories( categorySetToCategoryDtoSet( product.getCategories() ) );

        return productDto.build();
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.title( category.getTitle() );

        return categoryDto.build();
    }

    protected Set<CategoryDto> categorySetToCategoryDtoSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDto> set1 = new HashSet<CategoryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDto( category ) );
        }

        return set1;
    }
}
