package com.hrules.darealmvp.sample.presentation.presenters;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.commons.DebugLog;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.presentation.adapters.ListFragmentAdapterListener;
import java.util.ArrayList;
import java.util.List;

public class ListFragmentPresenter extends DRPresenter<ListFragmentPresenter.ListFragmentView>
    implements ListFragmentAdapterListener {
  private static final String BUNDLE_ITEMS = "BUNDLE_ITEMS";
  private static final String BUNDLE_TEST_BOOLEAN = "BUNDLE_TEST_BOOLEAN";

  private List<String> items;

  @Override protected void bind(@NonNull ListFragmentView view) {
    super.bind(view);
  }

  @Override public void onResume() {
    if (items != null) {
      getView().updateItems(items);
    } else {
      new AsyncTask<Void, Void, ArrayList<String>>() {
        @Override protected void onPreExecute() {
          getView().showProgress();
        }

        @Override protected ArrayList<String> doInBackground(Void... params) {
          try {
            Thread.sleep(3000);
          } catch (InterruptedException ignored) {
          }
          return retrieveItems();
        }

        @Override protected void onPostExecute(ArrayList<String> newItems) {
          getView().hideProgress();
          getView().updateItems(newItems);
        }
      }.execute();
    }
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putStringArrayList(BUNDLE_ITEMS, (ArrayList<String>) items);
    outState.putBoolean(BUNDLE_TEST_BOOLEAN, true);
    DebugLog.d("onSaveState");
  }

  @Override public void onLoadState(Bundle savedState) {
    items = savedState.getStringArrayList(BUNDLE_ITEMS);
    DebugLog.d("onLoadState:  " + savedState.getBoolean("BUNDLE_TEST_BOOLEAN"));
  }

  @Override public void onViewReady() {
    super.onViewReady();
    ListFragmentAdapter adapter = new ListFragmentAdapter(new ArrayList<String>(), this);
    getView().setAdapter(adapter);
  }

  private ArrayList<String> retrieveItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      items.add("Item" + i);
    }
    return items;
  }

  @Override public void unbind() {
    getView().unbind();
  }

  @Override public void onClick(String item) {
    getView().onClick(item);
  }

  public interface ListFragmentView extends DRView {
    void setAdapter(ListFragmentAdapter adapter);

    void unbind();

    void updateItems(List<String> items);

    void onClick(String item);

    void showProgress();

    void hideProgress();
  }
}
