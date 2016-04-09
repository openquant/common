package com.larroy.quant.common

/**
 * @author piotr 16.05.15
 */
object Resolution extends Enumeration {
  type Enum = Value
  val
    tick,
    _1_secs,
    _5_secs,
    _10_secs,
    _15_secs,
    _30_secs,
    _1_min,
    _2_mins,
    _3_mins,
    _5_mins,
    _10_mins,
    _15_mins,
    _20_mins,
    _30_mins,
    _1_hour,
    _4_hours,
    _1_day,
    _1_week = Value
}
