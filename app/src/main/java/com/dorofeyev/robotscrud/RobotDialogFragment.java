package com.dorofeyev.robotscrud;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RobotDialogFragment extends DialogFragment {

    private String title;
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_robot, container,
                false);
        getDialog().setTitle(title);

        Button addRobotButton = (Button) rootView.findViewById(R.id.buttonAddRobot);
        addRobotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClicked(view);
            }
        });

        return rootView;
    }

    public void addButtonClicked(View view) {
        ((MainActivity)getActivity()).buttonAddClicked(getView());
        dismiss();
    }



}