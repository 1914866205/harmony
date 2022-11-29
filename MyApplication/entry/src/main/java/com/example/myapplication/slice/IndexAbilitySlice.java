package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import com.example.myapplication.dao.StudentDaoImpl;
import com.example.myapplication.domain.Student;
import com.example.myapplication.util.DatabaseConnection;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

import java.sql.SQLException;

public class IndexAbilitySlice extends AbilitySlice {
    private Text id;
    private Text username;
    private Text password;
    private Text name;
    private Text age;
    private Text sex;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_index);
        id = (Text) findComponentById(ResourceTable.Id_index_id);
        username = (Text) findComponentById(ResourceTable.Id_index_username);
        password = (Text) findComponentById(ResourceTable.Id_index_password);
        name = (Text) findComponentById(ResourceTable.Id_index_name);
        age = (Text) findComponentById(ResourceTable.Id_index_age);
        sex = (Text) findComponentById(ResourceTable.Id_index_sex);

        String username = intent.getStringParam("username");
        String password = intent.getStringParam("password");
        this.username.setText(username);
        this.password.setText(password);
        DatabaseConnection dc = null;
        try {
            dc = new DatabaseConnection();
            StudentDaoImpl dao = new StudentDaoImpl(dc.getConnection());
            Student student = dao.login(username, password);
            this.username.setText(student.getUsername());
            this.password.setText(student.getPassword());
            this.id.setText(student.getId());
            this.name.setText(student.getName());
            this.age.setText(student.getAge());
            this.sex.setText(student.getSex());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
