package com.pillgood.drholmes.start;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.api.UserAPI;
import com.pillgood.drholmes.api.login.UserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends Activity {
    private Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button join, del;
        EditText email, name, password;
        CheckBox gender_man, gender_woman, emailagree;

        join = (Button) findViewById(R.id.join_button);
        del = (Button) findViewById(R.id.cancel);
        email = (EditText) findViewById(R.id.join_email);
        name = (EditText) findViewById(R.id.join_name);
        password = (EditText) findViewById(R.id.join_password);
        gender_man = (CheckBox) findViewById(R.id.man);
        gender_woman = (CheckBox) findViewById(R.id.woman);
        emailagree = (CheckBox) findViewById(R.id.emailagree);

        join.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String id = email.getText().toString();
                String pw = password.getText().toString();
                String n = name.getText().toString();
                //hideKeyboard();

                //로그인 정보 미입력 시
                if (id.trim().length() == 0 || pw.trim().length() == 0 || n.trim().length() == 0
                        || id == null || pw == null || n == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(join.getContext());
                    builder.setTitle("알림")
                            .setMessage("회원가입 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //로그인 통신
                    LoginResponse(id, pw);
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    public void LoginResponse(String id, String pw) {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://43.200.60.75:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI service = retrofit.create(UserAPI.class);

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        UserClass userClass = new UserClass(
                id,
                pw
        );

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        service.userLogin(userClass).enqueue(new Callback<List<UserClass>>() {

            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(JoinActivity.this, id + "님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                    intent.putExtra("userId", id);
                    startActivity(intent);
                    JoinActivity.this.finish();

//                    //response.body()를 result에 저장
//                    UserClass result = response.body();
//
//                    //받은 코드 저장
//                    String resultCode = result.getResultCode();
//
//                    //받은 토큰 저장
//                    String token = result.getToken();
//
//                    String success = "200"; //로그인 성공
//                    String errorId = "300"; //아이디 일치x
//                    String errorPw = "400"; //비밀번호 일치x
//
//
//                    if (resultCode.equals(success)) {
//                        String userID = idText.getText().toString();
//                        String userPassword = passwordText.getText().toString();
//
//                        //다른 통신을 하기 위해 token 저장
//                        //setPreference(token,token);
//
//                        //자동 로그인 여부
//
//                        Toast.makeText(LoginActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        intent.putExtra("userId", userID);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//
//                    } else if (resultCode.equals(errorId)) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("아이디가 존재하지 않습니다.\n 고객센터에 문의바랍니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//
//                    } else if (resultCode.equals(errorPw)) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("비밀번호가 일치하지 않습니다.\n 고객" +
//                                        "센터에 문의바랍니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//                    } else {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//
//                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
                Log.e("실패", "ERROR=" + t.toString());
            }
        });
    }

    //화면 터치 시 키보드 내려감
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
