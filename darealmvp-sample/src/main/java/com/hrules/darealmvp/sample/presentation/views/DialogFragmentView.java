package com.hrules.darealmvp.sample.presentation.views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatDialogFragment;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.DialogFragmentPresenter;

public class DialogFragmentView
    extends DRAppCompatDialogFragment<DialogFragmentPresenter, DialogFragmentPresenter.IDialog>
    implements DialogFragmentPresenter.IDialog {
  @Bind(R.id.text) TextView text;

  @Override protected AlertDialog.Builder getAlertDialog() {
    // a DialogFragment can still optionally be used as a normal fragment. Return NULL in that case.
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    View view = getActivity().getLayoutInflater().inflate(getLayoutResource(), null);
    initializeViews(view);

    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
      }
    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
      }
    });

    builder.setView(view);
    return builder;
  }

  @Override protected int getLayoutResource() {
    // a DialogFragment can still optionally be used as a normal fragment
    return R.layout.fragment_dialog;
  }

  @Override protected void initializeViews(View view) {
    // a DialogFragment can still optionally be used as a normal fragment
    ButterKnife.bind(this, view);
    text.setText(getString(R.string.app_name));
  }

  @Override public void unbind() {
    ButterKnife.unbind(this);
  }
}
