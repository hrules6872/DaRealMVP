package com.hrules.darealmvp.sample.presentation.views;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRFragmentV4;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.DebugLog;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.presentation.adapters.commons.ColorDividerItemDecoration;
import com.hrules.darealmvp.sample.presentation.presenters.ListFragmentPresenter;
import java.util.List;

@SuppressWarnings("WeakerAccess") public class ListFragmentView
    extends DRFragmentV4<ListFragmentPresenter, ListFragmentPresenter.ListFragmentView>
    implements ListFragmentPresenter.ListFragmentView {
  @Bind(R.id.progress) ProgressBar progress;
  @Bind(R.id.recyclerView) RecyclerView recyclerView;

  @Override public int getLayoutResource() {
    return R.layout.fragment_list;
  }

  @SuppressWarnings("deprecation") @Override public void initializeViews(View view) {
    ButterKnife.bind(this, view);
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    Resources res = getResources();
    recyclerView.addItemDecoration(
        new ColorDividerItemDecoration(res.getColor(android.R.color.darker_gray),
            res.getDimension(R.dimen.list_divider_size)));
  }

  @Override public void setAdapter(ListFragmentAdapter adapter) {
    recyclerView.setAdapter(adapter);
  }

  @Override public void unbind() {
    ButterKnife.unbind(this);
  }

  @Override public void updateItems(List<String> items) {
    ((ListFragmentAdapter) recyclerView.getAdapter()).updateItems(items);
  }

  @Override public void preCreateView() {
    DebugLog.d("preCreateView() called");
  }

  @Override public void onClick(String item) {
    Toast.makeText(getActivity(), item + " " + getString(R.string.item_clicked), Toast.LENGTH_SHORT)
        .show();
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
