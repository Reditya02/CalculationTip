package com.example.calculationtip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.calculationtip.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private val price: Price = Price()
    private val tip: Tip = Tip()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCalculate.setOnClickListener(this@MainActivity)
            switchRoundUp.setOnClickListener(this@MainActivity)
            rgTipOption.setOnCheckedChangeListener(this@MainActivity)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_calculate -> onCalculateButtonClicked(view)
            R.id.switch_round_up -> onSwitchClicked(view)
        }
    }

    private fun onSwitchClicked(view: View) {
        if (view is SwitchMaterial) {
            tip.isRoundTip = view.isChecked
            tip.totalTip = 0.0
        }
    }

    private fun onCalculateButtonClicked(view: View) {
        if (view is Button) {
            binding.apply {
                if (edtCost.text.toString().trim().isNotEmpty()) {
                    price.apply {
                        basePrice = edtCost.text.toString().toDouble()
                        Calculate.calculatePrice(tip, this)
                        tvTipResult.text = String.format("%.2f", totalPrice)
                    }
                } else {
                    edtCost.error = resources.getString(R.string.input_error)
                    tvTipResult.text = resources.getString(R.string.total_tip)
                }
            }
        }
    }

    override fun onCheckedChanged(rg: RadioGroup?, id: Int) {
        tip.tipPercentage = when (id) {
            R.id.rb_amazing -> 0.2F
            R.id.rb_good -> 0.18F
            R.id.rb_ok -> 0.15F
            else -> 0.0F
        }

        tip.totalTip = 0.0
    }
}