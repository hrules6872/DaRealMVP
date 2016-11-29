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

package com.hrules.darealmvp.sample.presentation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {
  private static List<String> items;
  private static ListFragmentAdapterListener listener;

  public ListFragmentAdapter(@NonNull ArrayList<String> items, @NonNull ListFragmentAdapterListener listener) {
    ListFragmentAdapter.items = items;
    ListFragmentAdapter.listener = listener;
  }

  @Override public ListFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ListFragmentAdapter.ViewHolder holder, int position) {
    holder.text1.setText(items.get(position));
  }

  @Override public int getItemCount() {
    return items.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text1) TextView text1;

    ViewHolder(View itemView) {
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

  public void updateItems(@NonNull List<String> items) {
    ListFragmentAdapter.items = items;
    notifyDataSetChanged();
  }
}
