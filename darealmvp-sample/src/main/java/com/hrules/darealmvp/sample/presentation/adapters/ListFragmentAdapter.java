package com.hrules.darealmvp.sample.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {
  private static List<String> items;
  private static ListFragmentAdapterListener listener;

  public ListFragmentAdapter(ArrayList<String> items, ListFragmentAdapterListener listener) {
    this.items = items;
    this.listener = listener;
  }

  @Override
  public ListFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ListFragmentAdapter.ViewHolder holder, int position) {
    holder.text1.setText(items.get(position));
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.text1) TextView text1;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (ListFragmentAdapter.listener != null) {
            listener.onClick(items.get(getAdapterPosition()));
          }
        }
      });
    }
  }

  public void updateItems(List<String> items) {
    this.items = items;
    notifyDataSetChanged();
  }
}
