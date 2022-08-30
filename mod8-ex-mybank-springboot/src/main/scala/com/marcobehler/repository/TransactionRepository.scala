package com.marcobehler.repository

import com.marcobehler.model.Transaction
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

import java.lang.{Iterable => JIterable}

trait TransactionRepository extends CrudRepository[Transaction, String] {
  @Query("select id, amount, reference, bank_slogan, receiving_user from transactions where receiving_user = :receivingUser")
  def findByReceivingUser(@Param("receivingUser") userId: String): JIterable[Transaction]
}
