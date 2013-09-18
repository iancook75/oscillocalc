package com.iansoft.oscillocalc;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View;
import android.widget.*;
import android.view.View.*;
import android.text.*;
import android.content.Intent;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//Start Time Per Division EditText
		EditText time_val = (EditText) findViewById(R.id.time_value);
		time_val.addTextChangedListener(new TextWatcher() {

				public void afterTextChanged(Editable s1) {
					freq_text_changed();
				}

				public void onTextChanged(CharSequence s1, int start, int before, int count) {

				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after){

				}
			}
		);
		//End Time Per Division EditText
		
		//Start Divisions EditText
		EditText p2p_val = (EditText) findViewById(R.id.divPeak2Peak);
		p2p_val.addTextChangedListener(new TextWatcher() {
			
			public void afterTextChanged(Editable s1) {
				freq_text_changed();
			}
			
			public void onTextChanged(CharSequence s1, int start, int before, int count) {
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after){
			
			}
		});
	
// App Function Spinner Begin		
		Spinner funct_spinner = (Spinner) findViewById(R.id.func_spinner);
		funct_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				Spinner funct_spinner2 = (Spinner) findViewById(R.id.func_spinner);
				
				if (funct_spinner2.getSelectedItem().equals("Frequency Calculator")) {
					//setContentView(R.layout.main);
				}
				if (funct_spinner2.getSelectedItem().equals("Voltage Calculator")) {
				//	sendMessage();
					Intent intent = new Intent(MainActivity.this, VoltageActivity.class);
					//EditText editText = (EditText) findViewById(R.id.edit_message);
					//String message = editText.getText().toString();
					//intent.putExtra(EXTRA_MESSAGE, message);
					startActivity(intent);
					funct_spinner2.setSelection(0);
				}
			}
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
		// Create an ArrayAdapter using the string array and a default spinner layout 
		ArrayAdapter<CharSequence> funcadapter = ArrayAdapter.createFromResource(
			this, R.array.app_functions,
			android.R.layout.simple_spinner_item
		); 
		// Specify the layout to use when the list of choices appears
		funcadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		funct_spinner.setAdapter(funcadapter);
//App Function Spinner End	
	
//Div Spinner Start		
		Spinner div_spinner = (Spinner) findViewById(R.id.divspinner); 
	
		// Create an ArrayAdapter using the string array and a default spinner layout 
		ArrayAdapter<CharSequence> divadapter = ArrayAdapter.createFromResource(
			this, R.array.div_units_array,
			android.R.layout.simple_spinner_item
		); 
		// Specify the layout to use when the list of choices appears
		divadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		div_spinner.setAdapter(divadapter);
//Div Spinner End
		
//Freq Spinner Start		
		Spinner spinner2 = (Spinner) findViewById(R.id.freq_spinner2); 
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
					freq_text_changed();
				}
				public void onNothingSelected(AdapterView<?> adapterView) {

				}
			}
		);
		// Create an ArrayAdapter using the string array and a default spinner layout 
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
			this, R.array.freq_units_array,
			android.R.layout.simple_spinner_item
		); 
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner2.setAdapter(adapter2);
//Freq Spinner End
		
		
//Time Spinner Start
		Spinner time_spinner = (Spinner) findViewById(R.id.time_spinner); 
		time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
					freq_text_changed();
				}
				public void onNothingSelected(AdapterView<?> adapterView) {

				}
			}
		);
		// Create an ArrayAdapter using the string array and a default spinner layout 
		ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(
			this, R.array.time_units_array,
			android.R.layout.simple_spinner_item
		); 
		// Specify the layout to use when the list of choices appears
		time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		time_spinner.setAdapter(time_adapter);
		
//Time Spinner End
	}
	
	

	public void freq_text_changed() {
		EditText freq_output = (EditText) findViewById(R.id.freq_val);
		EditText div_input = (EditText) findViewById(R.id.divPeak2Peak);
		EditText time_per_div_input = (EditText) findViewById(R.id.time_value);
		Spinner freq_spinner2 = (Spinner) findViewById(R.id.freq_spinner2);
		Spinner time_spinner = (Spinner) findViewById(R.id.time_spinner);
		
		float freq_output_scale = 0;
		float time_input_scale = 0;
		
		if (freq_spinner2.getSelectedItem().toString().equals("Hz")) {
			freq_output_scale = 1;	
		}
		
		if (freq_spinner2.getSelectedItem().toString().equals("kHz")) {
			freq_output_scale = 1000f;	
		}
		
		if (freq_spinner2.getSelectedItem().toString().equals("MHz")) {
			freq_output_scale = 1000000f;	
		}
		
		if (freq_spinner2.getSelectedItem().toString().equals("GHz")) {
			freq_output_scale = 1000000000f;	
		}
		
		if ( time_spinner.getSelectedItem().toString().equals("S")) {
			time_input_scale = 1;
		}
		
		if ( time_spinner.getSelectedItem().toString().equals("mS")) {
			time_input_scale = 1000f;
		}
		
		if ( time_spinner.getSelectedItem().toString().equals("uS")) {
			time_input_scale = 1000000f;
		}
		
		if ( time_spinner.getSelectedItem().toString().equals("nS")) {
			time_input_scale = 1000000000f;
		}
		
		String div_val_string = div_input.getText().toString();
		if (div_val_string.equals("")) {
			return;
		}
		
		if (div_val_string.equals("0")) {
			return;
		}
		
		float div_val = Float.parseFloat( div_val_string );
		
		String time_input_string = time_per_div_input.getText().toString();
		if (time_input_string.equals("")) {
			return;
		}
		
		if (time_input_string.equals("0")) {
			return;
		}
		
		float time_input_val = Float.parseFloat( time_input_string );
		
		float freq_output_val = ( 1 / ( (div_val * time_input_val) / time_input_scale)) / freq_output_scale;
		
		String freq_output_string = Float.toString(freq_output_val);
		
		freq_output.setText(freq_output_string);
	
	}
	
	public void sendMessage(View view) {
		Intent intent = new Intent(this, VoltageActivity.class);
		startActivity(intent);
	}
	
}
