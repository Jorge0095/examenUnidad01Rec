package com.example.examenunidad01rec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etUser = findViewById<EditText>(R.id.user)
        val etPass = findViewById<EditText>(R.id.pass)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        val expectedUser = etUser.hint.toString()
        val expectedPass = etPass.hint.toString()

        btnIngresar.setOnClickListener {
            val inputUser = etUser.text.toString()
            val inputPass = etPass.text.toString()

            if (inputUser == expectedUser && inputPass == expectedPass) {
                // Credenciales correctas - ir a CalculadoraActivity
                val intent = Intent(this, CalculadoraActivity::class.java)
                startActivity(intent)
            } else {
                // Credenciales incorrectas - mostrar Toast
                Toast.makeText(
                    this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnSalir.setOnClickListener {
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
}