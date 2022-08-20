package com.marcobehler.model

import scala.beans.BeanProperty

case class User(@BeanProperty var id: String, @BeanProperty var name: String)
