package com.example.calculationtip

import kotlin.math.ceil

object Calculate {

    private fun calculateTip(tip: Tip, price: Double) {
        tip.apply {
            totalTip = price * tipPercentage
            if (isRoundTip)
                totalTip = ceil(totalTip)
        }
    }

    fun calculatePrice(tip: Tip, price: Price) {
        if (tip.totalTip == 0.0)
            calculateTip(tip, price.basePrice)
        price.totalPrice = price.basePrice + tip.totalTip
    }

}