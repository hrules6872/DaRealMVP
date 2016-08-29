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

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrules.darealmvp.DRFragmentV4;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.fragments.PageFragmentPresenter;

public class PageFragmentView extends DRFragmentV4<PageFragmentPresenter, PageFragmentPresenter.Page>
    implements PageFragmentPresenter.Page {
  @BindView(R.id.text) TextView text;

  private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";

  private Unbinder unbinder;

  public PageFragmentView() {
  }

  public static PageFragmentView newInstance(int page) {
    PageFragmentView fragment = new PageFragmentView();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, page);
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_page;
  }

  @Override protected void initializeViews(View view) {
    unbinder = ButterKnife.bind(this, view);

    text.setText(getString(R.string.pager_pageNumber, getArguments().getInt(ARG_SECTION_NUMBER)));
  }

  @Override public void unbind() {
    if (unbinder != null) {
      unbinder.unbind();
    }
  }
}
