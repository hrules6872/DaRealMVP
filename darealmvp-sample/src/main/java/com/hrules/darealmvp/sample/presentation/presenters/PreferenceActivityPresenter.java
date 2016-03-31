package com.hrules.darealmvp.sample.presentation.presenters;

import android.preference.Preference;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.NotImplementedException;
import com.hrules.darealmvp.sample.presentation.views.PreferenceActivityView;

public class PreferenceActivityPresenter
    extends DRPresenter<PreferenceActivityPresenter.IPreferenceView> {

  public void onPreferenceClick(Preference preference) {
    switch (preference.getKey()) {
      case PreferenceActivityView.KEY_PREFS_GOTOREPO:
        getView().doGotoRepo(getViewContext().getString(R.string.app_repositoryUrl));
        break;

      default:
        throw new NotImplementedException();
    }
  }

  public interface IPreferenceView extends DRView {
    void doGotoRepo(String url);
  }
}
