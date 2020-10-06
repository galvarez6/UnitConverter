package edu.utep.cs.cs4330.unitconverter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    UnitConverter uni = new UnitConverter();
    TextView first,second, result;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        first = findViewById(R.id.initial_unit);
        second = findViewById(R.id.convertTo);
        result = findViewById(R.id.result);
        input = findViewById(R.id.unit_input);

        uni.setMode(UnitConverter.Mode.INCH_TO_CENTIMETER);

        first.setText(uni.fromUnit());
        second.setText(uni.toUnit());

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String c;
                double cc = 0.0;
                try{
                    c = input.getText().toString();
                    cc = Double.parseDouble(c);
                }
                catch (NumberFormatException e){
                    c = "0";
                }

                Log.d("msg","UNITS: " + cc);
                result.setText(String.valueOf(uni.convert(cc)));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void changeTextToMode(){
        first.setText(uni.fromUnit());
        second.setText(uni.toUnit());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.f_m) {
            uni.setMode(UnitConverter.Mode.FEET_TO_METER);
            changeTextToMode();
            //return true;
        }
        else if(id == R.id.i_c){
            uni.setMode(UnitConverter.Mode.INCH_TO_CENTIMETER);
            changeTextToMode();
        }
        else if(id == R.id.p_g){
            uni.setMode(UnitConverter.Mode.POUND_TO_GRAM);
            changeTextToMode();
        }
        else if(id == R.id.q){
            Log.d("msg", "QUIT");
        }

        return super.onOptionsItemSelected(item);
    }
}