package com.jupremator.sandbox.simpleCrud.model

import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface SimpleCrudMapper {
    @Mapping(target = "id", constant = "0")
    fun toEntity(simpleCrudCreateRequest: SimpleCrudCreateRequest): SimpleCrud

    fun toDto(simpleCrud: SimpleCrud): SimpleCrudResponse

    fun toDto(simpleCrud: List<SimpleCrud>): List<SimpleCrudResponse>

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(ignore = true, target = "id")
    fun partialUpdate(
        simpleCrudCreateRequest: SimpleCrudUpdateRequest,
        @MappingTarget simpleCrud: SimpleCrud,
    ): SimpleCrud
}
