package com.example.evara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    private TextInputEditText password,confirmPassword;
    private EditText userName,email;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        userName = findViewById(R.id.register_edt_username);
        email = findViewById(R.id.register_edt_email);
        password = findViewById(R.id.register_edt_password);
        confirmPassword = findViewById(R.id.register_edt_confirmpassword);
        register = findViewById(R.id.regist_btn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // validating if the text field is empty or not.
                if (userName.getText().toString().isEmpty()) {
                    userName.setError("Username is empty");
                }
                else if (email.getText().toString().isEmpty()) {
                    email.setError("Email is empty");
                }
                else if (password.getText().toString().isEmpty()) {
                    password.setError("Password is empty");
                }
                else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {

                    confirmPassword.setError("Passwords aren't similar");

                }

                // calling a method to post the data and passing our name and job.
                else {
                    add();
                }
            }
        });



    }

    public void add(){
        User user = new User(userName.getText().toString(), email.getText().toString(), password.getText().toString());
        Call<RegisterRespond> call = ApiClient.getInstance().getApi().addUser(user.getName(), user.getEmail(), user.getPassword());

        call.enqueue(new Callback<RegisterRespond>() {
            @Override
            public void onResponse(Call<RegisterRespond> call, Response<RegisterRespond> response) {

                Toast.makeText(getApplicationContext(), "Successed " + response.body().getStatus(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegisterRespond> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}