package com.marcobehler.service

import com.marcobehler.model.User
import org.springframework.stereotype.Component

import java.util.UUID

@Component
class UserService {
  def findById(id: String): User = {
    val randomName = UUID.randomUUID().toString
    User(id, randomName)
  }
}
