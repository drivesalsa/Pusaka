package id.beneranindonesia.pusaka.activities.Account;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.androidnetworking.AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.api.SignUpAPI;
import id.beneranindonesia.pusaka.api.Token;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgGender;
    private Button registerBtn;
    private GradientDrawable registerBtnShape;
    private EditText et_fullname, et_school, et_account_name, et_email, et_password, et_confirm_password;
    private SignUpAPI signUpAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_fullname         = findViewById(R.id.txt_full_name);
        et_school           = findViewById(R.id.txt_school);
        et_account_name     = findViewById(R.id.txt_account_name);
        et_email            = findViewById(R.id.txtEmail);
        et_password         = findViewById(R.id.txtPassword);
        et_confirm_password = findViewById(R.id.txt_confirm_password);

        AndroidNetworking.initialize(getApplicationContext());

        registerBtn = findViewById(R.id.btn_register);
        registerBtn.setOnClickListener(this);

        LayerDrawable layerDrawable = (LayerDrawable) registerBtn.getBackground();
        registerBtnShape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.drawable_button);
        //Set Default Color
        registerBtnShape.setColor(getResources().getColor(R.color.java_blue));

        Button backBtn = (Button) findViewById(R.id.btn_register_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rgGender = findViewById(R.id.rg_gender);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                ImageView imgGender = findViewById(R.id.img_gender);

                if (checkedId == R.id.radio_male) {
                    imgGender.setImageDrawable(getResources().getDrawable(R.drawable.boy, getApplicationContext().getTheme()));
                    registerBtnShape.setColor(getResources().getColor(R.color.java_blue));
                } else if (checkedId == R.id.radio_female) {
                    imgGender.setImageDrawable(getResources().getDrawable(R.drawable.girl, getApplicationContext().getTheme()));
                    registerBtnShape.setColor(getResources().getColor(R.color.pink));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == registerBtn) {
//            String fullname        = et_fullname.getText().toString();
//            String schollName      = et_school.getText().toString();
//            String accountName     = et_account_name.getText().toString();
//            String email           = et_email.getText().toString();
//            String password        = et_password.getText().toString();
//            String confirmPassword = et_confirm_password.getText().toString();
//            if (!password.equals(confirmPassword)) {
//                return;
//            }
            try {
                JSONObject json = new JSONObject();
                json.put("email", "testsignup@gmail.com");
                json.put("password", "riscel123");
                json.put("lang", "id");
                json.put("cardType", 1);
                json.put("cardImage", "woahhhah.jpg");
                json.put("cardNumber", "3882348484842");

                HashMap<String, String> params = new HashMap<>();
                params.put("pikachu", json.toString());

                signUpAPI = new SignUpAPI();
                signUpAPI.listener = new SignUpAPI.SignUpAPiListener() {
                    @Override
                    public void signUpSuccess() {
                        System.out.println("Sign up success");
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void signUpFailed(int errorCode, String message) {
                        System.out.println("Error code : " + errorCode);
                        System.out.println("Message : " + message);
                    }
                };
                signUpAPI.register(params);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}














































