package com.marcobehler.repository

import com.marcobehler.model.Invoice
import org.springframework.data.repository.CrudRepository

trait InvoiceRepository extends CrudRepository[Invoice, String] {
}
