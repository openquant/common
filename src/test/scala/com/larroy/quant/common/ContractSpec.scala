package com.larroy.quant.common

import org.specs2.mutable._

class ContractSpec extends Specification {
  "Contract" should {
    "create a contract of the correct type given args" in {
      Contract("MSFT", ContractType.Stock, "NASDAQ") === StockContract("MSFT", "NASDAQ")
      Contract("EUR.USD", ContractType.Currency, "IDEALPRO") === CurrencyContract("EUR.USD", "IDEALPRO")
      Contract("CL", ContractType.Future, "CME") === FutureContract("CL", "CME")
    }
  }
}