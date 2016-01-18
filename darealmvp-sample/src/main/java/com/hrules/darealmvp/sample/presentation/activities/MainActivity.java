package com.hrules.darealmvp.sample.presentation.activities;

import android.os.Bundle;
import android.view.Menu;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.MainActivityPresenter;

public abstract class MainActivity
    extends DRAppCompatActivity<MainActivityPresenter, MainActivityPresenter.IMainView>
    implements MainActivityPresenter.IMainView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new MainActivityPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
}
