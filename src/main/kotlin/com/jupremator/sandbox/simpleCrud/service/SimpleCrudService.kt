package com.jupremator.sandbox.simpleCrud.service

import com.jupremator.sandbox.exception.NoSuchSimpleCrudException
import com.jupremator.sandbox.simpleCrud.model.SimpleCrud
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudCreateRequest
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudMapper
import com.jupremator.sandbox.simpleCrud.model.SimpleCrudUpdateRequest
import com.jupremator.sandbox.simpleCrud.repository.SimpleCrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimpleCrudService(
    private val simpleCrudRepository: SimpleCrudRepository,
    private val simpleCrudMapper: SimpleCrudMapper,
) {
    @Transactional
    fun create(simpleCrudCreateRequest: SimpleCrudCreateRequest): SimpleCrud =
        simpleCrudRepository.save(simpleCrudMapper.toEntity(simpleCrudCreateRequest))

    @Transactional
    fun delete(id: Int) {
        simpleCrudRepository.deleteById(id)
    }

    @Transactional
    fun update(simpleCrudUpdateRequest: SimpleCrudUpdateRequest): SimpleCrud {
        val simpleCrud =
            simpleCrudRepository.findById(simpleCrudUpdateRequest.id).orElseThrow {
                NoSuchSimpleCrudException("$simpleCrudUpdateRequest.id")
            }

        return simpleCrudMapper.partialUpdate(simpleCrudUpdateRequest, simpleCrud)
    }

    @Transactional(readOnly = true)
    fun getById(id: Int): SimpleCrud = simpleCrudRepository.findById(id).orElseThrow { NoSuchSimpleCrudException("$id") }

    @Transactional(readOnly = true)
    fun getAll(): List<SimpleCrud> = simpleCrudRepository.findAll()
}
