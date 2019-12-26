package com.educational.nsutresources.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import com.educational.nsutresources.R;

public class DialogFragment extends androidx.fragment.app.DialogFragment {


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.delete_dialog)
                    .setPositiveButton(R.string.positive_confirmation, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            listener.onDialogPositiveClick(DialogFragment.this);
                        }
                    })
                    .setNegativeButton(R.string.negative_confirmation, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            listener.onDialogNegativeClick(DialogFragment.this);
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }
    }
