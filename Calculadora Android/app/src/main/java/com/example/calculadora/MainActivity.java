package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder input = new StringBuilder();
    private boolean isRadians = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.textView3);

        Switch switchDegreesRadians = findViewById(R.id.switch1);
        switchDegreesRadians.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRadians = isChecked;
            updateResult();
        });
    }

    public void onClickNumero(View view) {
        Button button = (Button) view;
        input.append(button.getText());
        updateResult();
    }

    public void onClickOperador(View view) {
        Button button = (Button) view;
        input.append(" ").append(button.getText()).append(" ");
        updateResult();
    }

    public void onClick_res(View view) {
        try {
            String expression = input.toString();
            // Realiza el cálculo de la expresión
            double result = evaluarExpresion(expression);
            // Muestra el resultado
            resultTextView.setText(String.valueOf(result));
            // Limpia la entrada
            input.setLength(0);
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }

    public void onClick_Sin(View view) {
        try {
            double value = Double.parseDouble(resultTextView.getText().toString());
            double result;
            if (isRadians) {
                result = Math.sin(value);
            } else {
                result = Math.sin(Math.toRadians(value));
            }
            resultTextView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTextView.setText("Error");
        }
    }

    public void onClick_Cos(View view) {
        try {
            double value = Double.parseDouble(resultTextView.getText().toString());
            double result;
            if (isRadians) {
                result = Math.cos(value);
            } else {
                result = Math.cos(Math.toRadians(value));
            }
            resultTextView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTextView.setText("Error");
        }
    }

    public void onClick_Tan(View view) {
        try {
            double value = Double.parseDouble(resultTextView.getText().toString());
            double result;
            if (isRadians) {
                result = Math.tan(value);
            } else {
                result = Math.tan(Math.toRadians(value));
            }
            resultTextView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTextView.setText("Error");
        }
    }

    public void onClick_AC(View view) {
        input.setLength(0);
        resultTextView.setText("");
    }

    private void updateResult() {
        resultTextView.setText(input.toString());
    }

    private double evaluarExpresion(String expression) {
        // Puedes implementar aquí la lógica de evaluación de expresiones matemáticas
        // Puedes usar la clase ScriptEngine o evaluar manualmente la expresión.
        // Aquí hay un ejemplo simple usando evaluar manualmente la expresión:
        String[] tokens = expression.split(" ");
        double operand1 = Double.parseDouble(tokens[0]);
        double operand2 = Double.parseDouble(tokens[2]);
        String operator = tokens[1];

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("División por cero");
                }
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }
}


