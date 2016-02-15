package com.hrules.darealmvp.sample.presentation.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.MainActivityPresenter;

public class MainActivityView
    extends DRAppCompatActivity<MainActivityPresenter, MainActivityPresenter.IMainView>
    implements MainActivityPresenter.IMainView {
  @Bind(R.id.message) TextView message;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.showMessage) Button showMessage;

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    getPresenter().onOptionsItemSelected(item);
    return true;
  }

  @Override public void showToast() {
    Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
  }

  @Override public void changeMessage(String message) {
    this.message.setText(message);
    showMessage.setEnabled(false);
  }

  public void showListActivity() {
    startActivity(new Intent(this, ListActivityView.class));
  }

  @Override public void showPreferencesActivity() {
    startActivity(new Intent(this, PreferenceActivityView.class));
  }

  @OnClick({ R.id.showMessage, R.id.showListActivity }) void onClickButton(Button button) {
    getPresenter().onClickButton(button);
  }
}
