package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

public class LoginAbilitySlice extends AbilitySlice {
    private TextField username;
    private TextField password;
    private Button loginBtn;
    private Button regBtn;


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
                System.out.println(username.getText());
                System.out.println(username);
                if (loginBtn != null && username.getText().equals("212210731119") && password.getText().equals("nitaotao")) {
                    present(new IndexAbilitySlice(), new Intent());
                    new ToastDialog(getContext()).setText("登录成功").show();
                } else {
                    new ToastDialog(getContext()).setText("用户名或密码错误").show();
                }
            }
        });
    }


}
