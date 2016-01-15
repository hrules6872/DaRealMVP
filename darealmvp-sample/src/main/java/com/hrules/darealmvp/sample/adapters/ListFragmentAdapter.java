package com.hrules.darealmvp.sample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {
  public ListFragmentAdapter(ArrayList<String> items, ListFragmentAdapterListener listener) {

  }

  @Override
  public ListFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ListFragmentAdapter.ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
