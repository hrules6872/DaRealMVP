package com.hrules.darealmvp.sample.presentation.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRFragmentV4;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.PageFragmentPresenter;

public class PageFragmentView
    extends DRFragmentV4<PageFragmentPresenter, PageFragmentPresenter.IPage>
    implements PageFragmentPresenter.IPage {
  @Bind(R.id.text) TextView text;

  private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";

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
    ButterKnife.bind(this, view);

    text.setText(getString(R.string.pager_pageNumber, getArguments().getInt(ARG_SECTION_NUMBER)));
  }

  @Override public void unbind() {
    ButterKnife.unbind(this);
  }
}
