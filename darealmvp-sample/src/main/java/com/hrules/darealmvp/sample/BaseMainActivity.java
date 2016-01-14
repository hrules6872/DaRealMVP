package com.hrules.darealmvp.sample;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;

public abstract class BaseMainActivity extends DRAppCompatActivity<SamplePresenter, SampleView>
    implements SampleView {

  @Bind(R.id.message) TextView message;

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new SamplePresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
  }
}
