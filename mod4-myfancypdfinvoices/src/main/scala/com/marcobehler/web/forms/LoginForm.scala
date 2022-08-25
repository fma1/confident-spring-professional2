package com.marcobehler.web.forms

import javax.validation.constraints.{NotBlank, Size}
import scala.beans.BeanProperty

class LoginForm {
  @BeanProperty
  @NotBlank
  @Size(min = 5, max = 7)
  var username, password = ""
}
