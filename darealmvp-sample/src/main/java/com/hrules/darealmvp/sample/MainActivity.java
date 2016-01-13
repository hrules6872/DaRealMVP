package com.hrules.darealmvp.sample;

import android.os.Bundle;
import android.widget.Toast;
import com.hrules.darealmvp.DRAppCompatActivity;

public class MainActivity extends DRAppCompatActivity<SamplePresenter, SampleView>
    implements SampleView {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void onResume() {
    super.onResume();
    getPresenter().loadImage();
  }

  @Override public void showToast() {
    Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();
  }
}
