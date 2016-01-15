package com.hrules.darealmvp.sample.presenters;

import android.os.Bundle;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.adapters.ListFragmentAdapterListener;
import java.util.ArrayList;
import java.util.List;

public class ListFragmentPresenter extends DRPresenter<ListFragmentPresenter.IListFragmentView>
    implements ListFragmentAdapterListener {
  private static final String BUNDLE_ITEMS = "BUNDLE_ITEMS";

  private ListFragmentAdapter adapter;
  private List<String> items;

  @Override public void onResume() {
    if (items != null) {

    }
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putStringArrayList(BUNDLE_ITEMS, (ArrayList<String>) items);
  }

  @Override public void onLoadState(Bundle savedState) {
    items = savedState.getStringArrayList(BUNDLE_ITEMS);
  }

  @Override public void onViewReady() {
    super.onViewReady();
    adapter = new ListFragmentAdapter(new ArrayList<String>(), this);
    view.setAdapter(adapter);
  }

  public void loadList() {

  }

  @Override public void unbind() {
    view.unbind();
    super.unbind();
  }

  public interface IListFragmentView extends DRView {
    void setAdapter(ListFragmentAdapter adapter);

    void unbind();
  }
}
