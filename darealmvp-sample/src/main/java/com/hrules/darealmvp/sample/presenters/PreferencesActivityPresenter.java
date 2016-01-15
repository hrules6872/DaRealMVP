package com.hrules.darealmvp.sample.presenters;

import android.preference.Preference;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.views.PreferenceActivityView;

public class PreferencesActivityPresenter
    extends DRPresenter<PreferencesActivityPresenter.IPreferencesView> {

  public void onPreferenceClick(Preference preference) {
    if (preference.getKey().equals(PreferenceActivityView.KEY_PREFS_GOTOREPO)) {
      view.doGotoRepo(view.getContext().getString(R.string.app_repository_url));
    }
  }

  public interface IPreferencesView extends DRView {
    void doGotoRepo(String url);
  }
}
