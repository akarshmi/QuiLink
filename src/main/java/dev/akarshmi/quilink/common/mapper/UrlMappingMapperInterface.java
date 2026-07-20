package dev.akarshmi.quilink.common.mapper;

import dev.akarshmi.quilink.shortener.document.UrlMapping;
import dev.akarshmi.quilink.shortener.dto.UrlMappingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(componentModel = "spring")
@MapperConfig(componentModel = "spring")
public interface UrlMappingMapperInterface {

    UrlMappingResponse urlMappingToUrlMappingResponse(UrlMapping urlMapping);
}
