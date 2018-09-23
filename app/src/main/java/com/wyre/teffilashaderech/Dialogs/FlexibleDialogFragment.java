package com.wyre.teffilashaderech.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import com.wyre.teffilashaderech.R;
public class FlexibleDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new AlertDialog.Builder(getContext())
                .setTitle(getArguments().getString("title"))
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(getArguments().getString("message")).setPositiveButton("OK", null)
                .create();
    }

public static void ShowDialog(FragmentManager manager,String title,String message){
    // Use the flexible dialog fragment to show the dialog
    FlexibleDialogFragment dial = new FlexibleDialogFragment();
    Bundle bundle = new Bundle();
    bundle.putString("title", title);
    bundle.putString("message", message);
    dial.setArguments(bundle);
    dial.show(manager, "about_dialog");
}
}

