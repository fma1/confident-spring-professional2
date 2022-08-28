package com.marcobehler.repository

import com.marcobehler.model.Invoice
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

import java.lang.{Iterable => JIterable}

trait InvoiceRepository extends CrudRepository[Invoice, String] {
  @Query("SELECT id, pdf_url, user_id, amount FROM \"invoices\" where user_id = :userId")
  def findByUserId(@Param("userId") userId: String): JIterable[Invoice]
}
