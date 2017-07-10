package com.example.sampaniccia.learningthings.Objects;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sampaniccia.learningthings.Activities.MainActivity;
import com.example.sampaniccia.learningthings.R;

/**
 * Created by Sam Paniccia on 7/3/2017.
 */

public class NewMemberDialog extends DialogFragment {

    public interface NoticeDialogListener{
        public void onDialogPositiveClick (Member member);
    }

    NewMemberDialog.NoticeDialogListener mListener;

    private MainActivity a;


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mListener = (NoticeDialogListener) activity;
        } catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstaneState){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog_New));
        builder.setTitle(R.string.event_newmem_title);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View thisView = inflater.inflate(R.layout.dialog_new_member, null);
        builder.setView(thisView);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                EditText content = (EditText) thisView.findViewById(R.id.event_newmem_name);
                CheckBox checkBox = (CheckBox) thisView.findViewById(R.id.event_newmem_checkbox);

                String name = String.valueOf(content.getText());
                boolean isAdmin = checkBox.isChecked();
                Drawable d = ContextCompat.getDrawable(a, R.drawable.test_profpic_32dp);

                Member m = new Member(name, name, isAdmin, d);
                mListener.onDialogPositiveClick(m);

            }
        });

        return builder.create();
    }

    public NewMemberDialog setActivity(MainActivity a){
        this.a = a;
        return this;
    }
}
