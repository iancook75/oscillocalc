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
			setContentView(R.layout.voltcalc);
			
			//Start Probe EditText input box
			EditText probe_val = (EditText) findViewById(R.id.attenuation_val);
			probe_val.setVisibility(View.INVISIBLE);
			probe_val.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s1) {
						calcVoltage();
					}

					public void onTextChanged(CharSequence s1, int start, int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after){

					}
				});
			//End Probe EditText input box
			
			//Start Probe Attenuation Spinner
			Spinner probe_spinner = (Spinner) findViewById(R.id.probe_spinner); 
			probe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
						Spinner probe_spinner = (Spinner) findViewById(R.id.probe_spinner); 
						if (probe_spinner.getSelectedItem().toString().equals("Custom")) {
							findViewById(R.id.attenuation_val).setVisibility(View.VISIBLE);	
						}
						else {
							findViewById(R.id.attenuation_val).setVisibility(View.INVISIBLE);
						}
						
						calcVoltage();
					}
					public void onNothingSelected(AdapterView<?> adapterView) {

					}
				}
			);
			// Create an ArrayAdapter using the string array and a default spinner layout 
			ArrayAdapter<CharSequence> probe_adapter = ArrayAdapter.createFromResource(
				this, R.array.probeUnits,
				android.R.layout.simple_spinner_item
			); 
			// Specify the layout to use when the list of choices appears
			probe_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			probe_spinner.setAdapter(probe_adapter);
			//End Probe Attenuation Spinner
			
			//Start Divisions EditText
			EditText divpervolt_val = (EditText) findViewById(R.id.volt_div_val);
			divpervolt_val.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s1) {
						calcVoltage();
					}

					public void onTextChanged(CharSequence s1, int start, int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after){

					}
				}
			);
			//End Div Per Volt EditText
			
			//Start Volts Per Division EditText
			EditText volt_val = (EditText) findViewById(R.id.volt_value);
			volt_val.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s1) {
						calcVoltage();
					}

					public void onTextChanged(CharSequence s1, int start, int before, int count) {

					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after){

					}
				}
			);
			//End Volts per Division EditText
			
			//Start App Function Spinner
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
			
			//Start Div Spinner		
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
			//End Div Spinner
			
			//Start Volt Spinner
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
			
			//End Volt Spinner
			
			//Start Volt Out Spinner		
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
			//End Volt Out Spinner
				
			
		}
		//Display Error when bad input detected
		public void blankError() {
			EditText volt_output = (EditText) findViewById(R.id.volt_val);
			volt_output.setHint("Input Error");
		}
		
		//Check Number
		public boolean isNumber(String str) { 
			try { 
				Float.parseFloat(str);
				return true;
				}
			catch (NumberFormatException nfe) {
				return false;
			} 
		}
		
		//Take input and calculate
		public void calcVoltage() {
			Spinner volt_spinner = (Spinner) findViewById(R.id.volt_spinner);
			Spinner volt_out_spinner = (Spinner) findViewById(R.id.out_volt_spinner);
			EditText volt_output = (EditText) findViewById(R.id.volt_val);
			EditText div_input = (EditText) findViewById(R.id.volt_value);
			EditText volt_per_div_input = (EditText) findViewById(R.id.volt_div_val);
			Spinner probe_spinner = (Spinner) findViewById(R.id.probe_spinner);
			EditText probe_custom = (EditText) findViewById(R.id.attenuation_val);

			float volt_output_scale = 0;
			float volt_input_scale = 0;
			float probe_atten_scale = 0;
			
			String div_val_string = div_input.getText().toString();
			if (!isNumber(div_val_string)) {
				blankError();
				return;
			}

			String volt_input_string = volt_per_div_input.getText().toString();
			if (!isNumber(volt_input_string)) {
				blankError();
				return;
			}
			
			
			if (probe_spinner.getSelectedItem().toString().equals("Custom")) {
				String probe_custom_string = probe_custom.getText().toString();
				if (!isNumber(probe_custom_string)) {
					blankError();
					return;
				}
				
				probe_atten_scale = Float.parseFloat( probe_custom_string );
				
			}
			
			if (probe_spinner.getSelectedItem().toString().equals("1x")) {
				probe_atten_scale = 1;
			}
			
			if (probe_spinner.getSelectedItem().toString().equals("10x")) {
				probe_atten_scale = 10;
			}
			
			if (probe_spinner.getSelectedItem().toString().equals("100x")) {
				probe_atten_scale = 100;
			}
			
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
			
			float div_val = Float.parseFloat( div_val_string );
			
			float volt_input_val = Float.parseFloat( volt_input_string );
			
			float volt_output_val = (((div_val * (probe_atten_scale * volt_input_val)) / volt_input_scale) * volt_output_scale);
			
			String volt_output_string = Float.toString(volt_output_val);
			
			volt_output.setHint(volt_output_string);
		}
	}
