package com.example.sampaniccia.learningthings.Objects;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sampaniccia.learningthings.R;


public class NewAnnouncementDialog extends DialogFragment {

    public static final String testName = "Nikki Potnick";

    public interface NoticeDialogListener{
        public void onDialogPositiveClick(Announcement announcement);
    }

    NewAnnouncementDialog.NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        } catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog_New));
        builder.setTitle(R.string.event_newann_title);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View thisView = inflater.inflate(R.layout.dialog_new_announcement, null);
        builder.setView(thisView);
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText content = (EditText) thisView.findViewById(R.id.event_newann_content);
                CheckBox checkBox = (CheckBox) thisView.findViewById(R.id.event_newann_checkbox);
                String title = NewAnnouncementDialog.testName;
                String message = String.valueOf(content.getText());
                Boolean isPinned = checkBox.isChecked();
                Announcement a = new Announcement(title, message, isPinned, true);
                mListener.onDialogPositiveClick(a);
            }


        });

        return builder.create();
    }

}
