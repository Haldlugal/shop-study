package ru.gb.gbshopmay.web.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto;
import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto.ManufacturerDtoBuilder;
import ru.gb.gbshopmay.entity.Manufacturer;
import ru.gb.gbshopmay.entity.Manufacturer.ManufacturerBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T16:23:59+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class ManufacturerMapperImpl implements ManufacturerMapper {

    @Override
    public Manufacturer toManufacturer(ManufacturerDto manufacturerDto) {
        if ( manufacturerDto == null ) {
            return null;
        }

        ManufacturerBuilder manufacturer = Manufacturer.builder();

        manufacturer.id( manufacturerDto.getId() );
        manufacturer.name( manufacturerDto.getName() );

        return manufacturer.build();
    }

    @Override
    public ManufacturerDto toManufacturerDto(Manufacturer manufacturer) {
        if ( manufacturer == null ) {
            return null;
        }

        ManufacturerDtoBuilder manufacturerDto = ManufacturerDto.builder();

        manufacturerDto.id( manufacturer.getId() );
        manufacturerDto.name( manufacturer.getName() );

        return manufacturerDto.build();
    }
}
