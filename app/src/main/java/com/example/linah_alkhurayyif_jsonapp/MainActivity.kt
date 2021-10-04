package com.example.linah_alkhurayyif_jsonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var curencyDetails: CurrencyDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputEUROamunt= findViewById<EditText>(R.id.EUROeditText)
        val convert = findViewById<Button>(R.id.convert)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val Date = findViewById<TextView>(R.id.Date)
        val Currency = arrayListOf("inr", "usd", "aud", "sar", "cny", "jpy")
        var selectedCurrency = 0
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, Currency
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    selectedCurrency = position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        convert.setOnClickListener {

            var userInput = inputEUROamunt.text.toString()
            if(userInput == ""){
                Toast.makeText(applicationContext, "Enter EURO value", Toast.LENGTH_SHORT).show();
            }else{
                var currency= userInput.toDouble()

                getCurrency(onResult = {
                    curencyDetails = it

                    Date.text ="Date: ${curencyDetails?.date.toString()}"
                    when (selectedCurrency) {
                        0 -> converResul(convertCurrency(curencyDetails?.eur?.inr?.toDouble(), currency));
                        1 -> converResul(convertCurrency(curencyDetails?.eur?.usd?.toDouble(), currency));
                        2 -> converResul(convertCurrency(curencyDetails?.eur?.aud?.toDouble(), currency));
                        3 -> converResul(convertCurrency(curencyDetails?.eur?.sar?.toDouble(), currency));
                        4 -> converResul(convertCurrency(curencyDetails?.eur?.cny?.toDouble(), currency));
                        5 -> converResul(convertCurrency(curencyDetails?.eur?.jpy?.toDouble(), currency));
                    }
                })
            }

        }

    }
    fun converResul(resul: Double) {

        val resultText = findViewById<TextView>(R.id.resultText)

        resultText.text = "Result: " + resul
    }

    private fun convertCurrency(selectedCurrency: Double?, userInput: Double): Double {
        var s = 0.0
        if (selectedCurrency != null) {
            s = (selectedCurrency * userInput)
        }
        return s
    }

    private fun getCurrency(onResult: (CurrencyDetails?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        if (apiInterface != null) {
            apiInterface.getCurrency()?.enqueue(object : Callback<CurrencyDetails> {
                override fun onResponse(
                    call: Call<CurrencyDetails>,
                    response: Response<CurrencyDetails>
                ) {
                    onResult(response.body())

                }

                override fun onFailure(call: Call<CurrencyDetails>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "Something Wrong...", Toast.LENGTH_SHORT).show();
                }

            })}}
}