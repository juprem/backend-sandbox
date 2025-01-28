package com.jupremator.sandbox.simpleCrud.repository

import com.jupremator.sandbox.simpleCrud.model.SimpleCrud
import org.springframework.data.jpa.repository.JpaRepository

interface SimpleCrudRepository : JpaRepository<SimpleCrud, Int>
