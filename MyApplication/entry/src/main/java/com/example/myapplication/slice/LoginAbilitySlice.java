package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.dao.StudentDaoImpl;
import com.example.myapplication.domain.Student;
import com.example.myapplication.util.DatabaseConnection;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class LoginAbilitySlice extends AbilitySlice {
    private TextField username;
    private TextField password;
    private Button loginBtn;
    private Button regBtn;
    //定义日志标签
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, "NTT_TAG");

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_login);

        username = (TextField) findComponentById(ResourceTable.Id_login_username);
        password = (TextField) findComponentById(ResourceTable.Id_login_password);

        loginBtn = (Button) findComponentById(ResourceTable.Id_login_Btn);
        regBtn = (Button) findComponentById(ResourceTable.Id_toRegBtn);


        /**
         * 跳转到注册页面
         */
        regBtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if (regBtn != null) {
                    present(new RegAbilitySlice(),
                            new Intent());
                }
            }
        });
        /**
         * 去登录
         */
        loginBtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

                if (loginBtn != null && username.getText().equals("ntt") && password.getText().equals("123")) {
                    present(new IndexAbilitySlice(), new Intent());
                    new ToastDialog(getContext()).setText("登录成功").show();
                } else {
                    new ToastDialog(getContext()).setText("用户名或密码错误").show();
                }
//                try {
//                    DatabaseConnection connection = new DatabaseConnection();
//                    StudentDaoImpl dao = new StudentDaoImpl(connection.getConnection());
//                    Student student = dao.login(username.getText(), password.getText());
//                    new ToastDialog(getContext()).setText("用户信息："+student+" |").show();
//                    if (student != null) {
//                        Intent intent = new Intent();
//                        intent.setParam("username", username.getText());
//                        intent.setParam("password", password.getText());
//                        present(new IndexAbilitySlice(), intent);
//                        new ToastDialog(getContext()).setText("登录成功").show();
//                    } else {
//                        new ToastDialog(getContext()).setText("用户名或密码错误").show();
//                    }
//                } catch (
//                        ClassNotFoundException | SQLException e) {
//                    e.printStackTrace();
//                }
            }

        });
    }


}
