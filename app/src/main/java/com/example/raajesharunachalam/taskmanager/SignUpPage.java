package com.example.raajesharunachalam.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raajesharunachalam.taskmanager.endpoints.UserEndpoints;
import com.example.raajesharunachalam.taskmanager.requests.CreateUserRequest;
import com.example.raajesharunachalam.taskmanager.responses.UIDResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        Button submit = (Button) findViewById(R.id.sumbit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitinfo();
            }
        });
    }

    public void submitinfo() {
        EditText first_name_edit = (EditText) findViewById(R.id.first_name);
        String first_name = first_name_edit.getText().toString();

        EditText last_name_edit = (EditText) findViewById(R.id.last_name);
        String last_name = last_name_edit.getText().toString();

        EditText emailfield = (EditText) findViewById(R.id.emailfield);
        String email = emailfield.getText().toString();

        EditText passwordfield = (EditText) findViewById(R.id.passwordfield);
        String password = passwordfield.getText().toString();

        CreateUserRequest request = new CreateUserRequest(first_name, last_name, email, password);
        Call<UIDResponse> call = UserEndpoints.userEndpoints.createUser(request);
        call.enqueue(new Callback<UIDResponse>() {
            @Override
            public void onResponse(Call<UIDResponse> call, Response<UIDResponse> response) {
                if (response.code() == ResponseCodes.HTTP_BAD_REQUEST) {
                    Toast.makeText(SignUpPage.this, "Email already used!", Toast.LENGTH_LONG).show();
                } else if (response.code() == ResponseCodes.HTTP_SERVER_ERROR) {
                    Toast.makeText(SignUpPage.this, "Server error.", Toast.LENGTH_LONG).show();
                } else if (response.code() == ResponseCodes.HTTP_CREATED) {
                    UIDResponse uidObject = response.body();
                    int uid = (int) uidObject.getUid();
                    Intent intent = new Intent(SignUpPage.this, GroupsActivity.class);
                    intent.putExtra("uid", IntentKeys.UID);
                    startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<UIDResponse> call, Throwable t) {

                Toast.makeText(SignUpPage.this, "Network Error, try again.", Toast.LENGTH_LONG).show();
            }
        });


    }

}
