package com.marcobehler.service

import com.marcobehler.model.User

import java.util.UUID

class UserService {
  def findById(id: String): User = {
    val randomName = UUID.randomUUID().toString
    User(id, randomName)
  }
}
