package com.marcobehler.dto

import com.fasterxml.jackson.annotation.JsonProperty

import javax.validation.constraints.{Max, Min, NotBlank}
import scala.beans.BeanProperty

class InvoiceDto {
  @JsonProperty("user_id")
  @NotBlank
  @BeanProperty
  var userId: String = _

  @Min(10)
  @Max(50)
  @BeanProperty
  var amount: Int = _
}
