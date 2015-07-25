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

import com.dorofeyev.robotscrud.model.Robot;

/**
 * Диалоговое окно для добавления/редактирования робота
 */
public class RobotDialogFragment extends DialogFragment {

    public enum DialogType {
        ADD_ROBOT, EDIT_ROBOT
    }
    private DialogType dialogType;
    public void setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
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

        // вешаем эвент на нажатие кнопки Confrim
        Button addRobotButton = (Button) rootView.findViewById(R.id.buttonConfirm);
        addRobotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmButtonClicked(view);
            }
        });

        // заполняем выпадающий список значениями
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.robot_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        switch (dialogType) {
            case ADD_ROBOT:
                getDialog().setTitle(getText(R.string.add_robot_dialog_header));
                break;
            case EDIT_ROBOT:
                getDialog().setTitle(getText(R.string.edit_robot_dialog_header));
                ((EditText)rootView.findViewById(R.id.editTextId)).setText(Integer.toString(robot.getId()));
                ((EditText)rootView.findViewById(R.id.editTextName)).setText(robot.getName());
                break;
        }

        return rootView;
    }

    /**
     * При нажатии кнопки Confirm передаем управление соответствующему методу в MainActivity
     * @param view
     */
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