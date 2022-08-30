package com.marcobehler.web

import com.marcobehler.dto.TransactionDto
import com.marcobehler.model.Transaction
import com.marcobehler.service.TransactionService
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import javax.validation.Valid
import java.lang.{Iterable => JIterable}

@RestController
class BankController(transactionService: TransactionService) {
  @GetMapping(Array("/transactions"))
  def transactions(): JIterable[Transaction] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@RequestBody @Valid transactionDto: TransactionDto): Transaction = {
    transactionService.create(transactionDto.amount, transactionDto.reference, transactionDto.receivingUser)
  }
}
