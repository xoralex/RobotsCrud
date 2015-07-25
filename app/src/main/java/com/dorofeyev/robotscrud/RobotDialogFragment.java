package com.dorofeyev.robotscrud;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RobotDialogFragment extends DialogFragment {

    public enum DialogType {
        ADD_ROBOT, EDIT_ROBOT
    }
    private DialogType dialogType;
    public void setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
    }

    private String title;
    public void setTitle(String title) {
        this.title = title;
    }

    private Robot robot;
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_robot, container,
                false);
        getDialog().setTitle(title);

        Button addRobotButton = (Button) rootView.findViewById(R.id.buttonConfirm);
        addRobotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmButtonClicked(view);
            }
        });

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.robot_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        switch (dialogType) {
            case ADD_ROBOT:
                break;
            case EDIT_ROBOT:
                ((EditText)rootView.findViewById(R.id.editTextId)).setText(Integer.toString(robot.getId()));
                ((EditText)rootView.findViewById(R.id.editTextName)).setText(robot.getName());
                break;
        }

        return rootView;
    }

    public void confirmButtonClicked(View view) {
        switch (dialogType) {
            case ADD_ROBOT:
                ((MainActivity)getActivity()).dialogAddRobotConfirmed(getView());
                break;
            case EDIT_ROBOT:
                ((MainActivity)getActivity()).dialogEditRobotConfirmed(getView());
                break;
        }
        dismiss();
    }



}