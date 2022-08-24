package com.marcobehler.dto

import javax.validation.constraints.{Max, Min, NotBlank}
import scala.beans.BeanProperty

class TransactionDto {
  @BeanProperty
  @Min(10)
  @Max(50)
  var amount: BigDecimal = _

  @BeanProperty
  @NotBlank
  var reference: String = _
}
