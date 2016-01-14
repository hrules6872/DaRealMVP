package com.hrules.darealmvp.sample.view.main;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.MainPresenter;

public abstract class BaseMainActivity
    extends DRAppCompatActivity<MainPresenter, MainPresenter.MainView>
    implements MainPresenter.MainView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new MainPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
  }
}
