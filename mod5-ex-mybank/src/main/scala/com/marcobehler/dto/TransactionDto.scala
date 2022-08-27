package com.marcobehler.dto

import javax.validation.constraints.{Max, Min, NotBlank, Size}
import scala.beans.BeanProperty

class TransactionDto {
  @BeanProperty
  @Min(10)
  @Max(50)
  var amount: BigDecimal = _

  @BeanProperty
  @NotBlank
  var reference: String = _

  @BeanProperty
  @NotBlank
  @Size(min = 1, max = 25)
  var receivingUser: String = _
}
