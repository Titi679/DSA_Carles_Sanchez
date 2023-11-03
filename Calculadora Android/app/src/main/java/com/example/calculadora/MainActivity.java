package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;
import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String operadorActual = "";
    private double numero1 = 0.0;
    private boolean nuevoNumero = true;
    private boolean enRadianes = true;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView3 = (TextView) findViewById(R.id.textView3);
        Switch switchModo = findViewById(R.id.switch1);
        switchModo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cambiar entre grados y radianes cuando cambia el estado del switch
            enRadianes = isChecked;
        });
    }
    public void onClickNumero(View view) {
        if (nuevoNumero) {
            textView3.setText("");
            nuevoNumero = false;
        }
        Button boton = (Button) view;
        String numero = boton.getText().toString();
        String textoActual = textView3.getText().toString();
        textView3.setText(textoActual + numero);
    }

    public void onClickOperador(View view) {
        Button boton = (Button) view;
        String operador = boton.getText().toString();

        if (!operadorActual.isEmpty()) {
            return; // Evitar que el usuario ingrese múltiples operadores consecutivos
        }

        operadorActual = operador;
        numero1 = Double.parseDouble(textView3.getText().toString());
        nuevoNumero = true;
    }

    public void onClick_res(View view) {
        if (operadorActual.isEmpty()) {
            return; // No se ha seleccionado un operador
        }

        double numero2 = Double.parseDouble(textView3.getText().toString());
        double resultado = 0.0;

        switch (operadorActual) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    textView3.setText("Error");
                    return;
                }
                break;
        }

        // Aplicar seno, coseno o tangente si corresponde
        if (view.getId() == R.id.button_Sin) {
            resultado = Math.sin(enRadianes ? Math.toRadians(resultado) : resultado);
        } else if (view.getId() == R.id.button_Cos) {
            resultado = Math.cos(enRadianes ? Math.toRadians(resultado) : resultado);
        } else if (view.getId() == R.id.button_Tan) {
            resultado = Math.tan(enRadianes ? Math.toRadians(resultado) : resultado);
        }

        // Formatear el resultado con dos decimales
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultadoFormateado = formato.format(resultado);

        textView3.setText(resultadoFormateado);
        operadorActual = "";
        nuevoNumero = true;
    }
    public void onClick_AC (View view) {
        textView3.setText("");
        operadorActual = "";
        nuevoNumero = true;
    }
}