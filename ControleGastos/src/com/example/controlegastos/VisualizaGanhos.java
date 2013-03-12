package com.example.controlegastos;

import java.util.List;

import com.example.controlegastos.R;
import com.example.controlegastos.R.id;
import com.example.controlegastos.R.layout;
import com.example.controlegastos.R.menu;

import models.Operacoes;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
@SuppressLint("NewApi")

public class VisualizaGanhos extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Operacoes> lista = Operacoes.findByPlanilha(1);
		int j = 0;
		setContentView(R.layout.activity_visualiza_ganhos);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout_ver_ganho);
		while(lista.size() > j){
			Operacoes ope = lista.get(j);
			LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			TextView tv=new TextView(this);
			tv.setLayoutParams(lparams);
			tv.setText(ope.getDescricao());
			layout.addView(tv);
			j++;
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_visualiza_ganhos, menu);
		return true;
	}

}
