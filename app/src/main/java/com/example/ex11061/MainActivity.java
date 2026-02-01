package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the main activity of the application.
 * It allows the user to input the parameters of a series and then view the series.
 * @author Itay Vaknin
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private Switch sw_type;
    private EditText et_a1;
    private EditText et_constant;
    private Intent si;
    private String[]sidra;
    private double a1;
    private double constant;
    private boolean type;


    /**
     * This method is called when the activity is first created.
     * It initializes the activity, sets the content view, and wires up the UI elements.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b>Note: Otherwise it is null.</b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw_type=findViewById(R.id.sw_type);
        et_a1=findViewById(R.id.et_a1);
        et_constant=findViewById(R.id.et_constant);
    }

    /**
     * This method checks if the input EditText fields contain valid numbers.
     * @return true if both fields contain valid numbers, false otherwise.
     */
    public boolean checkEt () {
        return (et_a1.getText().toString().matches("-?\\d*\\.?\\d+") && et_constant.getText().toString().matches("-?\\d*\\.?\\d+"));
    }

    /**
     * This method inflates the options menu.
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cred_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method handles menu item selections.
     * If the credits item is selected, it opens the CreditsActivity.
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     *         proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.credits) {
            si = new Intent(this,CreditActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the 'check' button is clicked.
     * It validates the user input, calculates the terms of the series (either arithmetic or geometric based on a switch),
     * and starts the CheckUpSidraActivity to display the results.
     * @param view The view that was clicked.
     */
    public void check(View view)
    {
        if(checkEt())
        {
            si=new Intent(this,CheckUpSidraActivity.class);
            type=sw_type.isChecked();
            a1=Double.parseDouble(et_a1.getText().toString());
            constant=Double.parseDouble(et_constant.getText().toString());
            si.putExtra("type",type);
            si.putExtra("a1",a1);
            si.putExtra("constant",constant);
            sidra=new String[20];
            sidra[0] = String.valueOf(a1);
            for (int i=1; i<20; i++) {
                sidra[i] = (type)?(String.valueOf(a1*Math.pow(constant,i))):(String.valueOf(a1+i*constant));
            }
            si.putExtra("sidra",sidra);
            startActivity(si);

        }

    }
}