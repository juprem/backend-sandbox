package com.jupremator.sandbox.simpleCrud.controller

import com.jupremator.sandbox.annotation.ControllerAnnotation
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudCreateRequest
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudMapper
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudResponse
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudUpdateRequest
import com.jupremator.sandbox.simpleCrud.service.SimpleCrudService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@ControllerAnnotation
class SimpleCrudController(
    private val simpleCrudService: SimpleCrudService,
    private val simpleCrudMapper: SimpleCrudMapper,
) {
    @GetMapping("/simple-crud")
    fun getAll(): ResponseEntity<List<SimpleCrudResponse>> = ResponseEntity.ok(simpleCrudMapper.toDto(simpleCrudService.getAll()))

    @GetMapping("/simple-crud/{id}")
    fun getById(
        @PathVariable id: Int,
    ): ResponseEntity<SimpleCrudResponse> = ResponseEntity.ok(simpleCrudMapper.toDto(simpleCrudService.getById(id)))

    @PostMapping("/simple-crud")
    fun createSimpleCrud(
        @RequestBody simpleCrudCreateRequest: SimpleCrudCreateRequest,
    ): ResponseEntity<SimpleCrudResponse> = ResponseEntity.ok(simpleCrudMapper.toDto(simpleCrudService.create(simpleCrudCreateRequest)))

    @PutMapping("/simple-crud")
    fun updateSimpleCrud(
        @RequestBody simpleCrudUpdateRequest: SimpleCrudUpdateRequest,
    ): ResponseEntity<SimpleCrudResponse> = ResponseEntity.ok(simpleCrudMapper.toDto(simpleCrudService.update(simpleCrudUpdateRequest)))

    @DeleteMapping("/simple-crud/{id}")
    fun deleteSimpleCrud(
        @PathVariable id: Int,
    ) = simpleCrudService.delete(id)
}
