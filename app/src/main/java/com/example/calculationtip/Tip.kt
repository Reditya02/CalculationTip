package com.example.calculationtip

data class Tip(
    var tipPercentage: Float = 0.2F,
    var totalTip: Double = 0.0,
    var isRoundTip: Boolean = false
)
