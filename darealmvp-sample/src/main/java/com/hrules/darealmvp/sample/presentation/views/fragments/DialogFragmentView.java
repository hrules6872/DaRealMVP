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

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrules.darealmvp.DRAppCompatDialogFragment;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.fragments.DialogFragmentPresenter;

public class DialogFragmentView extends DRAppCompatDialogFragment<DialogFragmentPresenter, DialogFragmentPresenter.Dialog>
    implements DialogFragmentPresenter.Dialog {
  @BindView(R.id.text) TextView text;

  private Unbinder unbinder;

  @Override protected AlertDialog.Builder getAlertDialog() {
    // a DialogFragment can still optionally be used as a normal fragment. Return NULL in that case.
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    View view = getActivity().getLayoutInflater().inflate(getLayoutResource(), null);
    initializeViews(view);

    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        // getPresenter.onClick(whichButton);
      }
    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        // getPresenter.onClick(whichButton);
      }
    });

    builder.setView(view);
    return builder;
  }

  @Override protected int getLayoutResource() {
    // a DialogFragment can still optionally be used as a normal fragment
    return R.layout.fragment_dialog;
  }

  @Override protected void initializeViews(@NonNull View view) {
    // a DialogFragment can still optionally be used as a normal fragment
    unbinder = ButterKnife.bind(this, view);

    text.setText(getString(R.string.app_name));
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }
}
