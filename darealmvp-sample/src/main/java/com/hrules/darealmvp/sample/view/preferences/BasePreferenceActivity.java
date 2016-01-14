package com.hrules.darealmvp.sample.view.preferences;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRPreferenceActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.PreferencesPresenter;

public class BasePreferenceActivity
    extends DRPreferenceActivity<PreferencesPresenter, PreferencesPresenter.PreferencesView>
    implements PreferencesPresenter.PreferencesView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new PreferencesPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_lizt;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
  }

  @Override protected int getPreferencesResource() {
    return 0;
  }
}
