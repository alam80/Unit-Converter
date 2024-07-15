package com.alam.unit__converter;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    private String[] units = {"Centimeters", "Meters", "Grams", "Kilograms"};
    private String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Set up the spinners with unit options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromUnit = units[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toUnit = units[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(inputStr);
        double result = 0.0;

        switch (fromUnit) {
            case "Centimeters":
                if (toUnit.equals("Meters")) {
                    result = input / 100;
                } else if (toUnit.equals("Centimeters")) {
                    result = input;
                }
                break;
            case "Meters":
                if (toUnit.equals("Centimeters")) {
                    result = input * 100;
                } else if (toUnit.equals("Meters")) {
                    result = input;
                }
                break;
            case "Grams":
                if (toUnit.equals("Kilograms")) {
                    result = input / 1000;
                } else if (toUnit.equals("Grams")) {
                    result = input;
                }
                break;
            case "Kilograms":
                if (toUnit.equals("Grams")) {
                    result = input * 1000;
                } else if (toUnit.equals("Kilograms")) {
                    result = input;
                }
                break;
        }

        resultTextView.setText("Result: " + result);
    }
}
