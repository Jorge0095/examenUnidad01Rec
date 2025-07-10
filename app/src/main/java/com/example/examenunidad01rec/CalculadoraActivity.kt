package com.example.examenunidad01rec

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var Num1: EditText
    private lateinit var Num2: EditText
    private lateinit var btnSuma: Button
    private lateinit var btnResta: Button
    private lateinit var btnMultiplicacion: Button
    private lateinit var btnDivision: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button
    private lateinit var Resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        iniciarVariables()
        iniciarBotones()
    }

    private fun iniciarVariables() {
        Num1 = findViewById(R.id.Num1)
        Num2 = findViewById(R.id.Num2)
        Resultado = findViewById(R.id.res)

        btnSuma = findViewById(R.id.suma)
        btnResta = findViewById(R.id.resta)
        btnMultiplicacion = findViewById(R.id.multiplicacion)
        btnDivision = findViewById(R.id.division)

        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
    }

    private fun iniciarBotones() {

        btnSuma.setOnClickListener { realizarOperacion(Operacion.SUMA) }
        btnResta.setOnClickListener { realizarOperacion(Operacion.RESTA) }
        btnMultiplicacion.setOnClickListener { realizarOperacion(Operacion.MULTIPLICACION) }
        btnDivision.setOnClickListener { realizarOperacion(Operacion.DIVISION) }

        btnLimpiar.setOnClickListener {
        Num1.text.clear()
        Num2.text.clear()
        Resultado.text = ""
        Toast.makeText(this, getString(R.string.btnLimpiar), Toast.LENGTH_SHORT).show()
        }

        btnRegresar.setOnClickListener {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("IMC")
        builder.setMessage("¿Desea Cerrar la Aplicación?")

        builder.setPositiveButton("Si") { dialog, which ->
            finish()
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(applicationContext,
                "Cancelar", Toast.LENGTH_SHORT).show()
                    }
        builder.setNeutralButton("Quiza") { dialog, which ->
            Toast.makeText(applicationContext,
                "Quiza", Toast.LENGTH_SHORT).show()
                    }

                    builder.show()
                }
            }

            private fun realizarOperacion(operacion: Operacion) {
                val num1Str = Num1.text.toString()
                val num2Str = Num2.text.toString()

                if (num1Str.isEmpty() || num2Str.isEmpty()) {
                    Toast.makeText(this, getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
                    return
                }

                try {
                    val num1 = num1Str.toDouble()
                    val num2 = num2Str.toDouble()
                    var resultado: Double = 0.0

                    when (operacion) {
                        Operacion.SUMA -> resultado = num1 + num2
                        Operacion.RESTA -> resultado = num1 - num2
                        Operacion.MULTIPLICACION -> resultado = num1 * num2
                        Operacion.DIVISION -> {
                            if (num2 == 0.0) {
                                Toast.makeText(this, getString(R.string.error_division_cero), Toast.LENGTH_SHORT).show()
                                return
                            }
                            resultado = num1 / num2
                        }
                    }

                    // Mostrar resultado (redondeado a 2 decimales si es necesario)
                    if (resultado == resultado.roundToInt().toDouble()) {
                        Resultado.text = resultado.roundToInt().toString()
                    } else {
                        Resultado.text = String.format("%.2f", resultado)
                    }

                } catch (e: NumberFormatException) {
                    Toast.makeText(this, getString(R.string.error_formato_numero), Toast.LENGTH_SHORT).show()
                }
            }

            // Enumeración para las operaciones
            private enum class Operacion {
                SUMA, RESTA, MULTIPLICACION, DIVISION
            }
        }

