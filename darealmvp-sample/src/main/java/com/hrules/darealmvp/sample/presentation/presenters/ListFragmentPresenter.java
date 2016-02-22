package com.hrules.darealmvp.sample.presentation.presenters;

import android.os.Bundle;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapterListener;
import java.util.ArrayList;
import java.util.List;

public class ListFragmentPresenter extends DRPresenter<ListFragmentPresenter.IListFragmentView>
    implements ListFragmentAdapterListener {
  private static final String BUNDLE_ITEMS = "BUNDLE_ITEMS";

  private List<String> items;

  @Override public void onResume() {
    if (items != null) {
      view.updateItems(items);
    }
    ArrayList<String> newItems = retrieveItems();
    view.updateItems(newItems);
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putStringArrayList(BUNDLE_ITEMS, (ArrayList<String>) items);
  }

  @Override public void onLoadState(Bundle savedState) {
    items = savedState.getStringArrayList(BUNDLE_ITEMS);
  }

  @Override public void onViewReady() {
    super.onViewReady();
    ListFragmentAdapter adapter = new ListFragmentAdapter(new ArrayList<String>(), this);
    view.setAdapter(adapter);
  }

  private ArrayList<String> retrieveItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      items.add("Item" + i);
    }
    return items;
  }

  @Override public void unbind() {
    view.unbind();
    super.unbind();
  }

  @Override public void onClick(String item) {
    view.onClick(item);
  }

  public interface IListFragmentView extends DRView {
    void setAdapter(ListFragmentAdapter adapter);

    void unbind();

    void updateItems(List<String> items);

    void onClick(String item);
  }
}
