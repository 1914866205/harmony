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

import java.sql.SQLException;

public class RegAbilitySlice extends AbilitySlice {
    private TextField username;
    private TextField password1;
    private TextField password2;
    private TextField name;
    private TextField age;
    private TextField sex;
    private Button regBtn;


    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_reg);

        username = (TextField) findComponentById(ResourceTable.Id_reg_username);
        password1 = (TextField) findComponentById(ResourceTable.Id_reg_password1);
        password2 = (TextField) findComponentById(ResourceTable.Id_reg_password2);
        name = (TextField) findComponentById(ResourceTable.Id_reg_name);
        age = (TextField) findComponentById(ResourceTable.Id_reg_age);
        sex = (TextField) findComponentById(ResourceTable.Id_reg_sex);

        regBtn = (Button) findComponentById(ResourceTable.Id_regBtn);
        /**
         * 注册完后去登录
         */
        regBtn.setClickedListener(component -> {
            try {
                if (!username.getText().equals(null) && !username.getText().equals("") && password1.getText().equals(password2.getText())) {
                    DatabaseConnection dc = new DatabaseConnection();
                    StudentDaoImpl dao = new StudentDaoImpl(dc.getConnection());
                    Student student = new Student();
                    student.setUsername(username.getText());
                    student.setPassword(password1.getText());
                    student.setName(name.getText());
                    student.setAge(Integer.parseInt(age.getText()));
                    student.setSex(sex.getText());
                    if (dao.reg(student)) {
                        new ToastDialog(getContext()).setText("注册成功！").show();
                        present(new IndexAbilitySlice(), new Intent());
                    } else {
                        new ToastDialog(getContext()).setText("注册失败请重试！").show();
                        present(new IndexAbilitySlice(), new Intent());
                    }
                } else {
                    new ToastDialog(getContext()).setText("账号为空或两次密码不一致").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


}
