package org.catanotes.test;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class CataNotesActivity extends Activity {

    private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        /** Recoje array vinos, productor */
        String[] vinos = getResources().getStringArray(R.array.vinos_array);
        String[] productores = getResources().getStringArray(R.array.productor_array);
        String[] regiones = getResources().getStringArray(R.array.region_array);
        String[] uvas = getResources().getStringArray(R.array.uva_array);
        
        Spinner spinner_grados = (Spinner) findViewById(R.id.spinner_grados);
        Spinner spinner_vista = (Spinner) findViewById(R.id.spinner_vista);
        Spinner spinner_tonalidad = (Spinner) findViewById(R.id.spinner_tonalidad);
        
        /** Autocoplete Vinos */
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_vino);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, vinos);
        textView.setAdapter(adapter);
        
        /** Autocoplete Productores */
        AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.autocomplete_productor);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, productores);
        textView2.setAdapter(adapter2);
        
        /** Autocoplete Regiones */
        AutoCompleteTextView textView3 = (AutoCompleteTextView) findViewById(R.id.autocomplete_region);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.list_item, regiones);
        textView3.setAdapter(adapter3);        
        
        /** Autocoplete Uvas */
        AutoCompleteTextView textView4 = (AutoCompleteTextView) findViewById(R.id.autocomplete_uva);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.list_item, uvas);
        textView4.setAdapter(adapter4);    
        
        
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(
                this, R.array.grados_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grados.setAdapter(adapter5);
        
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(
                this, R.array.vista_array, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vista.setAdapter(adapter6);
 
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(
                this, R.array.tonalidad, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tonalidad.setAdapter(adapter7);
        
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(
                this, R.array.tonalidad, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tonalidad.setAdapter(adapter8);
        
        
        // Capturar Elementos View
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // Agregar Click Listener al Boton
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // Fecha Actual
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Desplegar la fecha del método anterior
        updateDisplay();
  
    }
    
    // Actualizar la fecha en el TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    // El dato es actualizado cuando el usuario da click en set
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
            @Override
            protected Dialog onCreateDialog(int id) {
                switch (id) {
                case DATE_DIALOG_ID:
                    return new DatePickerDialog(this,
                                mDateSetListener,
                                mYear, mMonth, mDay);
                }
                return null;
            }

            
}