package com.hrules.darealmvp.sample.activities;

import android.os.Bundle;
import com.hrules.darealmvp.DRPreferencesActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presenters.PreferencesActivityPresenter;

public abstract class PreferencesActivity extends
    DRPreferencesActivity<PreferencesActivityPresenter, PreferencesActivityPresenter.IPreferencesView>
    implements PreferencesActivityPresenter.IPreferencesView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new PreferencesActivityPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_preferences;
  }

  @Override protected int getPreferencesResource() {
    return R.xml.preferences;
  }
}
