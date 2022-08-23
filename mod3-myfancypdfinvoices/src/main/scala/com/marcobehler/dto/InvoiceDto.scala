package com.marcobehler.dto

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

class InvoiceDto {
  @JsonProperty("user_id")
  @BeanProperty
  var userId: String = _
  @BeanProperty
  var amount: Int = _
}
