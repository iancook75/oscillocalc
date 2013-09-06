
	package com.iansoft.oscillocalc;

	import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.text.*;
import android.content.Intent;

	public class VoltageActivity extends Activity
	{
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.freqcalc);
			
			EditText divpervolt_val = (EditText) findViewById(R.id.volt_div_val);
			divpervolt_val.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s1) {
						calcVoltage();
					}

					public void onTextChanged(CharSequence s1, int start, int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after){

					}
				});
			EditText volt_val = (EditText) findViewById(R.id.volt_value);
			volt_val.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s1) {
						calcVoltage();
					}

					public void onTextChanged(CharSequence s1, int start, int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after){

					}
				});
			
//Start App Function Spinner
		//Start Add Spinner Listeners
			Spinner freq_funct_spinner = (Spinner) findViewById(R.id.freq_func_spinner);
			freq_funct_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
						Spinner freq_funct_spinner2 = (Spinner) findViewById(R.id.freq_func_spinner);

						if (freq_funct_spinner2.getSelectedItem().equals("Frequency Calculator")) {
							finish();
						}

					}
					public void onNothingSelected(AdapterView<?> adapterView) {

					}
				});
		//End Add Spinner Listeners
		//Populate Spinner
			// Create an ArrayAdapter using the string array and a default spinner layout 
			ArrayAdapter<CharSequence> freq_funcadapter = ArrayAdapter.createFromResource(
				this, R.array.freq_app_functions,
				android.R.layout.simple_spinner_item
			); 
			// Specify the layout to use when the list of choices appears
			freq_funcadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			freq_funct_spinner.setAdapter(freq_funcadapter);
		//End Populate Spinner
		
		
//End App Function Spinner
			
	//Div Spinner Start		
			Spinner freq_div_spinner = (Spinner) findViewById(R.id.freq_divspinner); 
		
			// Create an ArrayAdapter using the string array and a default spinner layout 
			ArrayAdapter<CharSequence> freq_divadapter = ArrayAdapter.createFromResource(
				this, R.array.div_units_array,
				android.R.layout.simple_spinner_item
			); 
			// Specify the layout to use when the list of choices appears
			freq_divadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			freq_div_spinner.setAdapter(freq_divadapter);
	//Div Spinner End
			
	//Volt Spinner Start
			Spinner volt_spinner = (Spinner) findViewById(R.id.volt_spinner); 
			volt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
						calcVoltage();
					}
					public void onNothingSelected(AdapterView<?> adapterView) {

					}
				}
			);
			// Create an ArrayAdapter using the string array and a default spinner layout 
			ArrayAdapter<CharSequence> volt_adapter = ArrayAdapter.createFromResource(
				this, R.array.voltUnits,
				android.R.layout.simple_spinner_item
			); 
			// Specify the layout to use when the list of choices appears
			volt_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			volt_spinner.setAdapter(volt_adapter);
			
	//Volt Spinner End
			
	//Volt Out Spinner Start		
			Spinner volt_out_spinner = (Spinner) findViewById(R.id.out_volt_spinner); 
			volt_out_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
						calcVoltage();
					}
					public void onNothingSelected(AdapterView<?> adapterView) {

					}
				}
			);
			// Create an ArrayAdapter using the string array and a default spinner layout 
			ArrayAdapter<CharSequence> volt_out_adapter = ArrayAdapter.createFromResource(
				this, R.array.voltUnits,
				android.R.layout.simple_spinner_item
			); 
			// Specify the layout to use when the list of choices appears
			volt_out_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			volt_out_spinner.setAdapter(volt_out_adapter);
	//Volt Out Spinner End
				
			
		}
		
		public void calcVoltage() {
			Spinner volt_spinner = (Spinner) findViewById(R.id.volt_spinner);
			Spinner volt_out_spinner = (Spinner) findViewById(R.id.out_volt_spinner);
			EditText volt_output = (EditText) findViewById(R.id.volt_val);
			EditText div_input = (EditText) findViewById(R.id.volt_value);
			EditText volt_per_div_input = (EditText) findViewById(R.id.volt_div_val);
			
			float volt_output_scale = 0;
			float volt_input_scale = 0;
			
			if (volt_out_spinner.getSelectedItem().toString().equals("V")) {
				volt_output_scale = 1;	
			}
			if (volt_out_spinner.getSelectedItem().toString().equals("mV")) {
				volt_output_scale = 1000;	
			}
			if (volt_out_spinner.getSelectedItem().toString().equals("uV")) {
				volt_output_scale = 1000000;	
			}
			if (volt_out_spinner.getSelectedItem().toString().equals("nV")) {
				volt_output_scale = 1000000000;	
			}
			
			if (volt_spinner.getSelectedItem().toString().equals("V")) {
				volt_input_scale = 1;	
			}
			if (volt_spinner.getSelectedItem().toString().equals("mV")) {
				volt_input_scale = 1000;	
			}
			if (volt_spinner.getSelectedItem().toString().equals("uV")) {
				volt_input_scale = 1000000;	
			}
			if (volt_spinner.getSelectedItem().toString().equals("nV")) {
				volt_input_scale = 1000000000;	
			}
			
			String div_val_string = div_input.getText().toString();
			if (div_val_string.equals("")) {
				return;
			}
			
			if (div_val_string.equals("0")) {
				return;
			}
			
			float div_val = Float.parseFloat( div_val_string );
			
			String volt_input_string = volt_per_div_input.getText().toString();
			if (volt_input_string.equals("")) {
				return;
			}
			if (volt_input_string.equals("0")) {
				return;
			}
			float volt_input_val = Float.parseFloat( volt_input_string );
			
			float volt_output_val = (((div_val * volt_input_val) / volt_input_scale) * volt_output_scale);
			
			String volt_output_string = Float.toString(volt_output_val);
			
			volt_output.setText(volt_output_string);
		}
	}
