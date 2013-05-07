package com.meimob.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SpinnerActivity extends Activity {
	
	void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        /*
	        setContentView(R.layout.spinner_1);

	        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                this, R.array.colors, android.R.layout.simple_spinner_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item){

							@Override
							public View getDropDownView(int position, View convertView,
									ViewGroup parent) {
								View view = getLayoutInflater().inflate(R.layout.detail_spinner_item, parent, false);

				                TextView label = (TextView) view.findViewById(R.id.text1);
				                label.setText(getItem(position));
				                if (spColor.getSelectedItemPosition() == position) {
				                    view.findViewById(R.id.icon).setVisibility(View.VISIBLE);
				                }
				                return view;
							}
					
				};
	        s1.setAdapter(adapter);
	        s1.setOnItemSelectedListener(
	                new OnItemSelectedListener() {
	                    public void onItemSelected(
	                            AdapterView<?> parent, View view, int position, long id) {
	                        showToast("Spinner1: position=" + position + " id=" + id);
	                    }

	                    public void onNothingSelected(AdapterView<?> parent) {
	                        showToast("Spinner1: unselected");
	                    }
	                });
	                */
	 }

}
