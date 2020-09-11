package com.moreino.gads2020;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationDialogFragment extends DialogFragment {

    private ApiClient mApiClient;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mGithubLink;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_confirmation, null);
        Button yesButton = view.findViewById(R.id.yes_button);
        ImageView cancelButton = view.findViewById(R.id.cancel_button);
        final Toast submittingToast = Toast.makeText(getActivity().getApplicationContext(), "Submitting your project...", Toast.LENGTH_LONG);
        mFirstName = getActivity().findViewById(R.id.first_name_form_field);
        mLastName = getActivity().findViewById(R.id.last_name_form_field);
        mEmail = getActivity().findViewById(R.id.email_form_field);
        mGithubLink = getActivity().findViewById(R.id.github_link_form_field);
        mApiClient = SubmissionClient.buildService(ApiClient.class);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submittingToast.show();
                Call<Void> call = mApiClient.submitProject(
                        mFirstName.getText().toString(),
                        mLastName.getText().toString(),
                        mEmail.getText().toString(),
                        mGithubLink.getText().toString()
                );
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dismiss();
                        submittingToast.cancel();
                        int responseCode = response.code();
                        if (responseCode == 200) {
                            SubmissionSuccess submission_successful_alert = new SubmissionSuccess();
                            submission_successful_alert.show(getActivity().getSupportFragmentManager(), null);

                        }
                        else {
                            SubmissionFailed submissionFailedAlert = new SubmissionFailed();
                            submissionFailedAlert.show(getActivity().getSupportFragmentManager(),null);
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Could not submit your project, Please verify your internet connection !", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();

    }
}