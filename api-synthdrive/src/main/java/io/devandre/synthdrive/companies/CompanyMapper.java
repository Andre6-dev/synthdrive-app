package io.devandre.synthdrive.companies;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    Company toEntity(CompanyResponse companyResponse);

    @Mapping(target = "createdAt", source = "createdAt")
    CompanyResponse toResponse(Company company);

    List<CompanyResponse> toResponseList(List<Company> companies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Company partialUpdate(
            CompanyResponse companyResponse,
            @MappingTarget Company company);

    default OffsetDateTime instantToOffsetDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.atOffset(ZoneOffset.UTC);
    }

    default Instant offsetDateTimeToInstant(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return offsetDateTime.toInstant();
    }
}