package com.hari.animalpuzzle.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import com.hari.animalpuzzle.R;

public class AboutActivity extends Activity {
    Context context;
	View _btn_priv_polity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.about);
		_btn_priv_polity=(View)findViewById(R.id.btn_priv_polity);
        _btn_priv_polity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			      Intent intent=new Intent(getApplicationContext(), PolicyActivity.class);
  			      startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
