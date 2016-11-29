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

package com.hrules.darealmvp.sample.presentation.presenters.activities;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;

public class ListActivityPresenter extends DRPresenter<ListActivityPresenter.ListView> {
  public void onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        getView().onBackPressed();
        break;
    }
  }

  public interface ListView extends DRView {
    void onBackPressed();
  }
}
