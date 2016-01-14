package com.hrules.darealmvp.sample.view.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.OnClick;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.view.list.LiztActivity;

public class MainActivity extends BaseMainActivity {

  @Bind(R.id.message) TextView message;

  @Override public void showToast() {
    Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
  }

  @Override public void changeMessage(String message) {
    this.message.setText(message);
  }

  @OnClick(R.id.showMessage) void onShowMessageClick(Button button) {
    getPresenter().showMessage();
  }

  @OnClick(R.id.showListActivity) void onShowListActivityClick(Button button) {
    getPresenter().showListActivity();
  }

  @Override public Context getContext() {
    return this.getApplicationContext();
  }

  public void showListActivity() {
    startActivity(new Intent(this, LiztActivity.class));
  }
}
