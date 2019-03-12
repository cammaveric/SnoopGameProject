package com.snoopgame.devices.dialog_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.snoopgame.devices.R;

public class ConnectionDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.connection_failed)
                .setMessage("Can't reach the " + getString(R.string.host))
                .setCancelable(false)
                .setPositiveButton(R.string.positiveBttn, (dialog, which) -> dialog.cancel());
        return builder.create();
    }
}
