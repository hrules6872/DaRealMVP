/*
 * Copyright (c) 2016. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.darealmvp.sample.presentation.presenters;

import android.preference.Preference;
import android.support.annotation.StringRes;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.NotImplementedException;
import com.hrules.darealmvp.sample.presentation.views.PreferenceActivityView;

public class PreferenceActivityPresenter
    extends DRPresenter<PreferenceActivityPresenter.PreferenceView> {

  public void onPreferenceClick(Preference preference) {
    switch (preference.getKey()) {
      case PreferenceActivityView.KEY_PREFS_GOTOREPO:
        getView().doGotoRepo(R.string.app_repositoryUrl);
        break;

      default:
        throw new NotImplementedException();
    }
  }

  public interface PreferenceView extends DRView {
    void doGotoRepo(@StringRes int url);
  }
}
