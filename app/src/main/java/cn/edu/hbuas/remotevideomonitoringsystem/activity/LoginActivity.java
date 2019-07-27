package cn.edu.hbuas.remotevideomonitoringsystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.hbuas.remotevideomonitoringsystem.R;
import cn.edu.hbuas.remotevideomonitoringsystem.bean.Constant;

public class LoginActivity extends Activity {

    EditText et_host, et_port;
    Button btn_login, btn_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        et_host = findViewById(R.id.hostEdit);
        et_port = findViewById(R.id.portEdit);

        btn_login = findViewById(R.id.loginBtn);
        btn_cancel = findViewById(R.id.cancelBtn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et_host.getText().toString();
                String port = et_port.getText().toString();
                if (url.equals("") || port.equals("") ) {
                    Toast.makeText(LoginActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    Constant.URL = et_host.getText().toString();
                    Constant.PORT = et_port.getText().toString();
//                    Toast.makeText(LoginActivity.this, "(" + url.getClass().toString() + "," + port + ")", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_host.setText("");
                et_port.setText("");
            }
        });
    }
}
