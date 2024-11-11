package com.aman.javamvvm;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.javamvvm.adapter.UserAdapter;
import com.aman.javamvvm.data.User;
import com.aman.javamvvm.viewmodel.UserViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText editTextName, editTextAge;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        Button buttonAddUser = findViewById(R.id.buttonAddUser);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(new ArrayList<>());

        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, users -> userAdapter.setUsers(users));

        buttonAddUser.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            int age = Integer.parseInt(editTextAge.getText().toString());

            User user = new User(name,age);

            userViewModel.insert(user);
            editTextName.setText("");
            editTextAge.setText("");

        });
    }
}