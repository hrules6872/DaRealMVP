package com.hrules.darealmvp.sample.activities;

import android.os.Bundle;
import android.view.Menu;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presenters.MainPresenter;

public abstract class MainActivity
    extends DRAppCompatActivity<MainPresenter, MainPresenter.IMainView>
    implements MainPresenter.IMainView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new MainPresenter());
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
