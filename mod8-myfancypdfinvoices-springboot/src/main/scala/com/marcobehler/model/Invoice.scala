package com.marcobehler.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.{Id, Version}
import org.springframework.data.relational.core.mapping.Table

import java.util.UUID
import scala.beans.BeanProperty

@Table("invoices")
case class Invoice(@BeanProperty @JsonProperty("user_id") var userId: String,
                   @BeanProperty var amount: Int,
                   @BeanProperty @JsonProperty("pdf_url") var pdfUrl: String) {
  @Id
  @BeanProperty
  var id: String = UUID.randomUUID().toString

  @Version
  var version: Int = _

  def this() = this("", 0, "")
}
