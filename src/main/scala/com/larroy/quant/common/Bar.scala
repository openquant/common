package com.larroy.quant.common

case class Bar(
  date: Long,
  open: Double,
  close: Double,
  high: Double,
  low: Double,
  adjClose: Double,
  volume: Double,
  source: String)
