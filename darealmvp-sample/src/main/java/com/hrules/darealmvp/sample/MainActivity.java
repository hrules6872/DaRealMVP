package com.hrules.darealmvp.sample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.hrules.darealmvp.DRAppCompatActivity;

public class MainActivity extends DRAppCompatActivity<SamplePresenter, SampleView>
    implements SampleView {

  private TextView message;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public void initializeViews() {
    message = (TextView) findViewById(R.id.message);
  }

  @Override public void showToast() {
    Toast.makeText(this, "WELCOME", Toast.LENGTH_LONG).show(); // TODO
  }

  @Override public void changeMessage(String message) {
    this.message.setText(message);
  }
}
