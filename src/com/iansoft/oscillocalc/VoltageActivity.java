
	package com.iansoft.oscillocalc;

	import android.app.*;
	import android.os.*;
	import android.view.*;
	import android.view.View;
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
			
			Spinner freq_funct_spinner = (Spinner) findViewById(R.id.freq_func_spinner);
			freq_funct_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
						Spinner freq_funct_spinner2 = (Spinner) findViewById(R.id.freq_func_spinner);

						if (freq_funct_spinner2.getSelectedItem().equals("Frequency Calculator")) {
							finish();
						//	Intent intent = new Intent(VoltageActivity.this, MainActivity.class);
						//	startActivity(intent);
						}
			//			if (funct_spinner2.getSelectedItem().equals("Voltage Calculator")) {
							
						
			//			}
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
		
		
//App Function Spinner End	
			
		}
	}
