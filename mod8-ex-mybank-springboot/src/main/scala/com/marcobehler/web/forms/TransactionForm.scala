package com.marcobehler.web.forms

import javax.validation.constraints.{DecimalMin, Max, NotBlank, NotNull, Size}
import scala.beans.BeanProperty

import java.math.{BigDecimal => JBigDecimal}

class TransactionForm {
  @BeanProperty
  @NotBlank
  @Size(min = 1, max = 25)
  var receivingUser: String = _

  @BeanProperty
  @NotBlank
  var reference: String = _

  @BeanProperty
  @NotNull
  @DecimalMin("0.01")
  @Max(100)
  var amount: JBigDecimal = _
}
