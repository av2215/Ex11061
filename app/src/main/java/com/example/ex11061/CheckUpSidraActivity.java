package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity displays the generated series in a Spinner.
 * It allows the user to select an item in the series and view its position, next item in the series, and sum.
 * @author Itay Vaknin
 * @version 1.0
 */
public class CheckUpSidraActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnCreateContextMenuListener {
    private Spinner a;
    private TextView tv_descrip;
    private Intent gi;
    private boolean type;
    private double a1;
    private double constant;
    private String[] sidra;
    private String[] sidraDisplay;
    private ArrayAdapter<String> adapter;


    /**
     * This method is called when the activity is first created.
     * It initializes the activity, retrieves the series data from the intent,
     * and sets up the ListView to display the series.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b>Note: Otherwise it is null.</b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up_sidra);
        a=findViewById(R.id.spinner);
        tv_descrip=findViewById(R.id.textView);
        gi=getIntent();
        type=gi.getBooleanExtra("type",false);
        a1=gi.getDoubleExtra("a1",0);
        constant=gi.getDoubleExtra("constant",0);
        sidra=gi.getStringArrayExtra("sidra");
        sidraDisplay = new String[sidra.length+1];
        sidraDisplay[0] = "Choose an item";
        for (int i=1; i<sidra.length+1; i++)
        {
            sidraDisplay[i] = sidra[i-1];
        }
        adapter=new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sidraDisplay);
        a.setAdapter(adapter);
        a.setOnItemSelectedListener(this);
    }

    /**
     * This method is called when the 'back' button is clicked.
     * It returns to the MainActivity.
     * @param view The view that was clicked.
     */
    public void back(View view) {
        finish();
    }

    /**
     * This method is called when an item in the spinner is selected.
     * It calculates the sum of the series up to the selected item and displays the item's position,
     * the next item in the series, and the sum.
     *
     * @param adapterView The AdapterView where the selection happened.
     * @param view The view within the AdapterView that was clicked.
     * @param i The position of the view in the adapter.
     * @param l The row id of the item that is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i!=0)
        {
            double sum;
            if(!type) // arithmetic
            {
                sum=(i+1)/2.0*(2*a1+i*constant);
            }
            else // geometric
            {
                if(constant==1)
                {
                    sum=a1*(i+1);
                }
                else
                {
                    sum=a1*(1-Math.pow(constant,i+1))/(1-constant);
                }
            }
            tv_descrip.setText("position: "+i+"\n nextInSidrs: "+((i==sidra.length)?"no next":sidra[i])+"\nsum: "+sum);

        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param adapterView The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
            gi = new Intent(this,CreditActivity.class);
            startActivity(gi);
        }
        return super.onOptionsItemSelected(item);
    }
}