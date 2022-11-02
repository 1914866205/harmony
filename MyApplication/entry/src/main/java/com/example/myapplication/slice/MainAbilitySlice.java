package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;

public class MainAbilitySlice extends AbilitySlice {
    private Button button1,button2;
    private TextField text_name;
    private TextField text_password;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);


        button1 = (Button) findComponentById(ResourceTable.);

        button2 = (Button) findComponentById(ResourceTable.lo);
        button2 = (Button) findComponentById(ResourceTable.Id_login_btn1);
        text_name = (TextField) findComponentById(ResourceTable.Id_text_name);
        text_password = (TextField) findComponentById(ResourceTable.Id_text_password);

    }

    private void zhuce(){

        // 为按钮设置点击回调
        button1.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if(button1!=null){
                    present(new LoginAbilitySlice(),new Intent());
                }
            }
        });
    };


    private void login(){
        button2.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

                if(button2!=null && name.equals("蛟龙腾飞") && password.equals("2019")){
                    present(new HomeAbilitySlice(),new Intent());
                    new ToastDialog(context).setText("登录成功").show();
                }else {
                    new ToastDialog(context).setText("用户名或密码有误请重新输入").show();
                }
            }
        });

    };


}
