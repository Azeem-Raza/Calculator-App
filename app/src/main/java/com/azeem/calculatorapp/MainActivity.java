package com.azeem.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // UI element for displaying numbers and results
    TextView display;

    // Variables to store the current operator and the numbers involved in the calculation
    String currentOperator;
    double firstNumber, secondNumber;

     //onCreate method - Called when the activity is first created.
     //It initializes the UI and sets up listeners for the buttons.
     // @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     // this Bundle contains the data it most recently supplied. Otherwise, it is null.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the display TextView where the numbers and results are shown
        display = findViewById(R.id.display);
        currentOperator = "";  // No operator selected initially
        firstNumber = 0;       // Default value for the first number

        // Number Buttons - Setting up listeners for number button clicks (0-9)
        findViewById(R.id.btn0).setOnClickListener(v -> appendNumber("0"));
        findViewById(R.id.btn1).setOnClickListener(v -> appendNumber("1"));
        findViewById(R.id.btn2).setOnClickListener(v -> appendNumber("2"));
        findViewById(R.id.btn3).setOnClickListener(v -> appendNumber("3"));
        findViewById(R.id.btn4).setOnClickListener(v -> appendNumber("4"));
        findViewById(R.id.btn5).setOnClickListener(v -> appendNumber("5"));
        findViewById(R.id.btn6).setOnClickListener(v -> appendNumber("6"));
        findViewById(R.id.btn7).setOnClickListener(v -> appendNumber("7"));
        findViewById(R.id.btn8).setOnClickListener(v -> appendNumber("8"));
        findViewById(R.id.btn9).setOnClickListener(v -> appendNumber("9"));

        // Operator Buttons - Setting up listeners for operator button clicks (+, -, x, /, %)
        findViewById(R.id.btnPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("x"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.btnPercent).setOnClickListener(v -> setOperator("%"));

        // Equal Button - Listener for the "=" button to calculate the result
        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());

        // Clear Button - Listener for the "C" button to reset/clear the display
        findViewById(R.id.btnClear).setOnClickListener(v -> clearDisplay());
    }


     //* Appends a number to the display when a number button is clicked.
     //* If the display shows "0", it is replaced by the clicked number.
     //* @param number The number to append to the display.

    private void appendNumber(String number) {
        if (display.getText().toString().equals("0")) {
            // If the current display is "0", replace it with the new number
            display.setText(number);
        } else {
            // Otherwise, append the number to the existing display
            display.append(number);
        }
    }


     //* Sets the current operator when an operator button (+, -, x, /, %) is clicked.
     //* The first number is taken from the display, and the operator is stored for the next operation.
     //* @param operator The operator selected by the user.



    private void setOperator(String operator) {
        // Parse the first number from the display and store the selected operator
        firstNumber = Double.parseDouble(display.getText().toString());
        currentOperator = operator;
        // Reset the display to "0" to prepare for the second number input
        display.setText("0");
    }

    /**
     * Calculates the result based on the selected operator and the two numbers.
     * The result is then displayed in the TextView.
     */
    private void calculateResult() {
        // Parse the second number from the display
        secondNumber = Double.parseDouble(display.getText().toString());
        double result = 0; // Variable to store the result of the calculation

        // Perform the calculation based on the current operator
        switch (currentOperator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "x":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    // Ensure the second number is not zero to avoid division by zero error
                    result = firstNumber / secondNumber;
                } else {
                    // Display "Error" if division by zero is attempted
                    display.setText("Error");
                    return;
                }
                break;
            case "%":
                // Calculate percentage: firstNumber * (secondNumber / 100)
                result = firstNumber * secondNumber / 100;
                break;
        }

        // Display the result in the TextView
        display.setText(String.valueOf(result));
    }

    /**
     * Clears the display and resets the calculator state.
     * This method is called when the "C" (clear) button is clicked.
     */
    private void clearDisplay() {
        display.setText("0");   // Reset the display to "0"
        firstNumber = 0;        // Reset the first number
        secondNumber = 0;       // Reset the second number
        currentOperator = "";   // Clear the current operator
    }
}
//add everything in array