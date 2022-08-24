package com.marcobehler.web

import com.marcobehler.dto.TransactionDto
import com.marcobehler.model.Transaction
import com.marcobehler.service.TransactionService
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid

@RestController
class BankController(transactionService: TransactionService) {
  @GetMapping(Array("/transactions"))
  def transactions(): JList[Transaction] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@RequestBody @Valid transactionDto: TransactionDto): Transaction = {
    transactionService.create(transactionDto.amount, transactionDto.reference)
  }
}
