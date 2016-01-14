package com.hrules.darealmvp.sample;

import android.widget.Toast;

public class MainActivity extends BaseMainActivity {
  @Override public void showToast() {
    Toast.makeText(this, "WELCOME", Toast.LENGTH_LONG).show(); // TODO
  }

  @Override public void changeMessage(String message) {
    this.message.setText(message);
  }
}
