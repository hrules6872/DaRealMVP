package com.hrules.darealmvp.sample.activities;

import android.os.Bundle;
import com.hrules.darealmvp.DRPreferenceActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presenters.PreferenceActivityPresenter;

public abstract class PreferenceActivity extends
    DRPreferenceActivity<PreferenceActivityPresenter, PreferenceActivityPresenter.IPreferenceView>
    implements PreferenceActivityPresenter.IPreferenceView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new PreferenceActivityPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_preference;
  }

  @Override protected int getPreferencesResource() {
    return R.xml.preferences;
  }
}
