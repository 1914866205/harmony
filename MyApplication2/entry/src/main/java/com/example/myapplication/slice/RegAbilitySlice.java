package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

public class RegAbilitySlice extends AbilitySlice {
    private TextField username;
    private TextField password1;
    private TextField password2;
    private Button regBtn;


    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_reg);

        username = (TextField) findComponentById(ResourceTable.Id_reg_username);
        password1 = (TextField) findComponentById(ResourceTable.Id_reg_password1);
        password2 = (TextField) findComponentById(ResourceTable.Id_reg_password2);

        regBtn = (Button) findComponentById(ResourceTable.Id_regBtn);
        /**
         * 注册完后去登录
         */
        regBtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if (!username.getText().equals(null) && !username.getText().equals("") && password1.getText().equals(password2.getText())) {
                    new ToastDialog(getContext()).setText("注册成功！").show();
                    present(new IndexAbilitySlice(), new Intent());
                } else {
                    new ToastDialog(getContext()).setText("账号为空或两次密码不一致").show();
                }
            }
        });
    }


}
