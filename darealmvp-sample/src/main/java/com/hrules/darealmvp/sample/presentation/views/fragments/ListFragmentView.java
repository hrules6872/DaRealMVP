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

package com.hrules.darealmvp.sample.presentation.views.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.DebugLog;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.presentation.adapters.commons.ColorDividerItemDecoration;
import com.hrules.darealmvp.sample.presentation.presenters.fragments.ListFragmentPresenter;
import java.util.List;

@SuppressWarnings("WeakerAccess") public class ListFragmentView extends Fragment
    implements ListFragmentPresenter.ListFragmentView {
  @Bind(R.id.progress) ProgressBar progress;
  @Bind(R.id.recyclerView) RecyclerView recyclerView;

  private ListFragmentPresenter presenter;

  //region Composition over inheritance
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    preCreateView();
    return inflater.inflate(getLayoutResource(), container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter = new ListFragmentPresenter();
    getPresenter().bind(this);
    initializeViews(view);
    if (savedInstanceState != null) {
      getPresenter().onLoadState(savedInstanceState);
    }
    getPresenter().onViewReady();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getPresenter().onSaveState(outState);
  }

  @Override public void onResume() {
    super.onResume();
    getPresenter().onResume();
  }

  private ListFragmentPresenter getPresenter() {
    return presenter;
  }

  public int getLayoutResource() {
    return R.layout.fragment_list;
  }

  @SuppressWarnings("deprecation") public void initializeViews(View view) {
    ButterKnife.bind(this, view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    Resources res = getResources();
    recyclerView.addItemDecoration(new ColorDividerItemDecoration(res.getColor(android.R.color.darker_gray),
        res.getDimension(R.dimen.list_divider_size)));
  }
  //endregion

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbind();
  }

  @Override public void setAdapter(ListFragmentAdapter adapter) {
    recyclerView.setAdapter(adapter);
  }

  public void unbind() {
    ButterKnife.unbind(this);
  }

  @Override public void updateItems(List<String> items) {
    ((ListFragmentAdapter) recyclerView.getAdapter()).updateItems(items);
  }

  public void preCreateView() {
    DebugLog.d("preCreateView() called");
  }

  @Override public void onClick(String item) {
    Toast.makeText(getActivity(), item + " " + getString(R.string.item_clicked), Toast.LENGTH_SHORT).show();
  }

  @Override public void showProgress() {
    progress.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
  }

  @Override public void hideProgress() {
    progress.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }
}
