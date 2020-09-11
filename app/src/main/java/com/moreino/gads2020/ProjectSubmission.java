package com.moreino.gads2020;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectSubmission extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        Button submitFormButton = findViewById(R.id.submit_form_button);
        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfirmationDialog();
            }
        });
    }

    private void openConfirmationDialog() {
        ConfirmationDialogFragment confirmationDialog = new ConfirmationDialogFragment();
        confirmationDialog.show(getSupportFragmentManager(),null);
    }
}
