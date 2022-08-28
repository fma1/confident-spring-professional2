package com.marcobehler.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.util.UUID
import scala.beans.BeanProperty

case class Invoice(@BeanProperty @JsonProperty("user_id") var userId: String,
                   @BeanProperty var amount: Int,
                   @BeanProperty @JsonProperty("pdf_url") var pdfUrl: String) {
  @BeanProperty
  var id: String = UUID.randomUUID().toString

  def this() = this("", 0, "")
}
