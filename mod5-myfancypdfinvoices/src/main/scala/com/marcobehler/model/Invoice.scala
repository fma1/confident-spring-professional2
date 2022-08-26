package com.marcobehler.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.util.UUID
import scala.beans.BeanProperty

case class Invoice(@BeanProperty @JsonProperty("user_id") userId: String,
                   @BeanProperty amount: Int,
                   @BeanProperty @JsonProperty("pdf_url") pdfUrl: String) {
  @BeanProperty
  var id: String = UUID.randomUUID().toString
}
