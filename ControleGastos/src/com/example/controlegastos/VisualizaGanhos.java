package com.example.controlegastos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VisualizaGanhos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiza_ganhos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_visualiza_ganhos, menu);
		return true;
	}

}
