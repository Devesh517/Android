package com.weee.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView, outputTextView;
    private String input = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.textView);
        outputTextView = findViewById(R.id.textView2);

        // Number Buttons
        int[] numberIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8,
                R.id.button9, R.id.button17}; // 0 is button17

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                input += btn.getText().toString();
                inputTextView.setText(input);
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // Operator Buttons
        findViewById(R.id.buttonadd).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonmi).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttonmu).setOnClickListener(operatorClickListener);
        findViewById(R.id.buttondiv).setOnClickListener(operatorClickListener);

        // Special Buttons
        findViewById(R.id.button18).setOnClickListener(new View.OnClickListener() { // Dot (.)
            @Override
            public void onClick(View v) {
                if (!input.contains(".")) {
                    input += ".";
                    inputTextView.setText(input);
                }
            }
        });

        findViewById(R.id.button19).setOnClickListener(new View.OnClickListener() { // Equal (=)
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() { // Clear All (C)
            @Override
            public void onClick(View v) {
                input = "";
                operator = "";
                firstNumber = 0;
                isOperatorClicked = false;
                inputTextView.setText("Input");
                outputTextView.setText("Output");
            }
        });

        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() { // Clear Entry (CE)
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    input = input.substring(0, input.length() - 1);
                    inputTextView.setText(input);
                }
            }
        });

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() { // 1/x
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    double value = Double.parseDouble(input);
                    if (value != 0) {
                        double result = 1 / value;
                        input = String.valueOf(result);
                        inputTextView.setText(input);
                    }
                }
            }
        });

        findViewById(R.id.button16).setOnClickListener(new View.OnClickListener() { // %
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    double value = Double.parseDouble(input);
                    double result = value / 100;
                    input = String.valueOf(result);
                    inputTextView.setText(input);
                }
            }
        });
    }

    private final View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            if (!input.isEmpty()) {
                firstNumber = Double.parseDouble(input);
                operator = btn.getText().toString();
                isOperatorClicked = true;
                input = "";
            }
        }
    };

    private void calculateResult() {
        if (isOperatorClicked && !input.isEmpty()) {
            double secondNumber = Double.parseDouble(input);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "−":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        outputTextView.setText("Error: Divide by 0");
                        return;
                    }
                    break;
            }
            outputTextView.setText(String.valueOf(result));
            inputTextView.setText(String.valueOf(result));
            input = String.valueOf(result);
            isOperatorClicked = false;
        }
    }
}
