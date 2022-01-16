package com.authapp;import androidx.appcompat.app.AppCompatActivity;import android.app.ProgressDialog;import android.content.Intent;import android.content.SharedPreferences;import android.os.AsyncTask;import android.os.Bundle;import android.view.View;import android.widget.EditText;import android.widget.LinearLayout;import android.widget.Toast;import com.authapp.API.APICall;import com.authapp.POJO.AllUser;import com.authapp.POJO.User;import java.util.ArrayList;import java.util.regex.Matcher;import java.util.regex.Pattern;import static com.authapp.LoginActivity.MyPREFERENCES;import static com.authapp.LoginActivity.UserID;public class PasswordChangeActivity extends AppCompatActivity implements View.OnClickListener {    private EditText editOPassword, editPassword, editCPassword;    private LinearLayout btnRegister;    private String user_id, sopass, spass, scpass;    private SharedPreferences sharedPreferences;    private ArrayList<User> resultList;    private String utype = "User";    private Bundle extra;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_password_change);        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);        editPassword = (EditText) findViewById(R.id.etPassword);        editCPassword = (EditText) findViewById(R.id.etCPassword);        editOPassword = (EditText) findViewById(R.id.etOPassword);        btnRegister = (LinearLayout) findViewById(R.id.btnRegister);        btnRegister.setOnClickListener(this);        resultList = new ArrayList<User>();        user_id = sharedPreferences.getString(UserID, "").trim();    }    @Override    public void onClick(View v) {        if (v == btnRegister) {            spass = editPassword.getText().toString().trim();            scpass = editCPassword.getText().toString().trim();            sopass = editOPassword.getText().toString().trim();            if (sopass.equals("")) {                editOPassword.setError("Enter Current Password");                return;            }            else if (!isValidPassword(sopass.toCharArray())) {                editOPassword.setError("Password length should be 8-20 and must contain character, number and any special character like # $ % ^ & + = ");                return;            }            if (spass.equals("")) {                editPassword.setError("Enter New Password");                return;            }            else if (!isValidPassword(spass.toCharArray())) {                editPassword.setError("Password length should be 8-20 and must contain character, number and any special character like # $ % ^ & + = ");                return;            }            else if (scpass.equals("")) {                editCPassword.setError("Enter New Password");                return;            }            else if (!isValidPassword(scpass.toCharArray())) {                editCPassword.setError("Password length should be 8-20 and must contain character, number and any special character like # $ % ^ & + = ");                return;            }else {                if (!spass.equals(scpass)) {                    Toast.makeText(PasswordChangeActivity.this, "Enter same New Password", Toast.LENGTH_SHORT).show();                    return;                } else {                    new PostPasswordUpdate().execute();                }            }        }    }    public class PostPasswordUpdate extends AsyncTask<Void, Void, AllUser> {        private ProgressDialog dialoug;        @Override        protected void onPreExecute() {            super.onPreExecute();            dialoug = new ProgressDialog(PasswordChangeActivity.this);            dialoug.setMessage("Updating.. Please Wait..");            dialoug.show();            dialoug.setCancelable(false);        }        @Override        protected AllUser doInBackground(Void... voids) {            AllUser result = new AllUser();            result = new APICall().PostPasswordUpdate(utype, user_id, spass, sopass);            return result;        }        @Override        protected void onPostExecute(AllUser allUser) {            super.onPostExecute(allUser);            if (allUser != null) {                resultList.clear();                resultList.addAll(allUser.getData());                String result = resultList.get(0).getResult().toString().trim();                String msg = resultList.get(0).getMsg().toString().trim();                //           Toast.makeText(RegisterActivity.this,result+"  " + msg,Toast.LENGTH_SHORT).show();                if (result.contains("No")) {                    if (msg.contains("Password")) {                        Toast.makeText(PasswordChangeActivity.this, "Invalid Current Password.", Toast.LENGTH_SHORT).show();                    } else if (msg.contains("NoData")) {                        Toast.makeText(PasswordChangeActivity.this, "Error in Server Connection.", Toast.LENGTH_SHORT).show();                    }                } else if (result.contains("Yes")) {                    if (msg.contains("Sucess")) {                        Toast.makeText(PasswordChangeActivity.this, "Password Updated", Toast.LENGTH_LONG).show();                        Intent login = new Intent(PasswordChangeActivity.this, HomePageActivity.class);                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);                        startActivity(login);                        finish();                    }                }            }            dialoug.dismiss();        }    }    // validating email id    private boolean isValidEmail(String email) {        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"                + "[A-Za-z]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$";        Pattern pattern = Pattern.compile(EMAIL_PATTERN);        Matcher matcher = pattern.matcher(email);        return matcher.matches();    }    // validating mobile no    private boolean isValidMobile(String mobile) {        String Mobile_PATTERN = "[0-9]{10}";        Pattern pattern = Pattern.compile(Mobile_PATTERN);        Matcher matcher = pattern.matcher(mobile);        return matcher.matches();    }    // validating only Charater    private boolean isValidOnlyChar(String Char) {        String CHAR_PATTERN = "[a-zA-Z]+";        Pattern pattern = Pattern.compile(CHAR_PATTERN);        Matcher matcher = pattern.matcher(Char);        return matcher.matches();    }    // validating only Number    private boolean isValidOnlyNumber(String Char) {        String CHAR_PATTERN = "[0-9]+";        Pattern pattern = Pattern.compile(CHAR_PATTERN);        Matcher matcher = pattern.matcher(Char);        return matcher.matches();    }    boolean isValidPassword(char[] pass) {        int letters = 0, digits = 0, specialChars = 0;        if (pass.length < 8 || pass.length > 20) {            return false;        }        for (int i = 0; i < pass.length; i++) {            char ch = pass[i];            if (Character.isLetter(ch)) {                letters++;            }            else if (Character.isDigit(ch)) {                digits++;            }            else if (isSpecialChar(ch)) {                specialChars++;            }            else if (Character.isWhitespace(ch)) {                return false;            }        }        return !(letters < 2 || digits < 1 || specialChars < 1);    }    static boolean isSpecialChar(char c) {        switch (c) {            case '@':            case '#':            case '$':            case '%':            case '^':            case '&':            case '+':            case '=':                return true;            default:                return false;        }    }}