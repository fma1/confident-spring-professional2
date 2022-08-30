package com.marcobehler.service

import com.marcobehler.model.Transaction
import com.marcobehler.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.lang.{Iterable => JIterable}
import java.math.{BigDecimal => JBigDecimal}

@Component
class TransactionService(transactionRepository: TransactionRepository, @Value("${bank.slogan}") bankSlogan: String) {
  @Transactional
  def findAll(): JIterable[Transaction] = {
    transactionRepository.findAll()
  }

  @Transactional
  def findByReceivingUser(userId: String): JIterable[Transaction] = {
    transactionRepository.findByReceivingUser(userId)
  }

  @Transactional
  def create(amount: JBigDecimal, reference: String, receivingUser: String): Transaction = {
    val transaction = Transaction(amount, reference, bankSlogan, receivingUser)
    transactionRepository.save(transaction)
  }
}