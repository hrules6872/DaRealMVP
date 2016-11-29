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

package com.hrules.darealmvp.sample.presentation.presenters.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.commons.DebugLog;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapterListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListFragmentPresenter extends DRPresenter<ListFragmentPresenter.ListFragmentView> implements ListFragmentAdapterListener {
  private static final String BUNDLE_ITEMS = "BUNDLE_ITEMS";
  private static final String BUNDLE_TEST_BOOLEAN = "BUNDLE_TEST_BOOLEAN";

  private List<String> items = new ArrayList<>();
  private ListFragmentAdapter adapter;

  @Override public void bind(@NonNull ListFragmentView view) {
    super.bind(view);
    adapter = new ListFragmentAdapter((ArrayList<String>) items, this);
  }

  @Override public void onViewReady(@Nullable Bundle savedInstanceState) {
    super.onViewReady(savedInstanceState);
    getView().setAdapter(adapter);

    if (savedInstanceState != null) {
      items = savedInstanceState.getStringArrayList(BUNDLE_ITEMS);
      DebugLog.d("savedInstanceState:  " + savedInstanceState.getBoolean("BUNDLE_TEST_BOOLEAN"));
    }
  }

  public void onResume() {
    if (!items.isEmpty()) {
      getView().hideProgress();
      getView().updateItems(items);
    } else {
      new AsyncTask<Void, Void, List<String>>() {
        @Override protected void onPreExecute() {
          getView().showProgress();
        }

        @Override protected List<String> doInBackground(Void... params) {
          try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
          } catch (InterruptedException ignored) {
          }
          items = retrieveItems();
          return items;
        }

        @Override protected void onPostExecute(List<String> newItems) {
          getView().hideProgress();
          getView().updateItems(newItems);
        }
      }.execute();
    }
  }

  public void onSaveInstanceState(@NonNull Bundle outState) {
    outState.putStringArrayList(BUNDLE_ITEMS, (ArrayList<String>) items);
    outState.putBoolean(BUNDLE_TEST_BOOLEAN, true);
    DebugLog.d("onSaveInstanceState");
  }

  private ArrayList<String> retrieveItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      items.add("Item" + i);
    }
    return items;
  }

  @Override public void onClick(@NonNull String item) {
    getView().onClick(item);
  }

  public interface ListFragmentView extends DRView {
    void setAdapter(@NonNull ListFragmentAdapter adapter);

    void updateItems(@NonNull List<String> items);

    void onClick(@NonNull String item);

    void showProgress();

    void hideProgress();
  }
}
