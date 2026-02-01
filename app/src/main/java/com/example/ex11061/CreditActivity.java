package com.example.ex11061;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity displays the credits of the application.
 * @author Itay Vaknin
 * @version 1.0
 */
public class CreditActivity extends AppCompatActivity {

    /**
     * This method is called when the activity is first created.
     * It sets the content view to the credits activity layout.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b>Note: Otherwise it is null.</b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    /**
     * This method is called when the 'back' button is clicked.
     * It returns to the MainActivity.
     * @param view The view that was clicked.
     */
    public void back(View view) {
        finish();
    }
}