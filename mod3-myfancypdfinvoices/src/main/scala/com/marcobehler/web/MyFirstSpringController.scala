package com.marcobehler.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, ResponseBody}

@Controller
class MyFirstSpringController {

  @GetMapping(Array("/"))
  @ResponseBody
  def index(): String = {
    "Hello World"
  }
}
