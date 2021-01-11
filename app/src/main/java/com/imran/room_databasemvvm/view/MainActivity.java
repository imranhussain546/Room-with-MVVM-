package com.imran.room_databasemvvm.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imran.room_databasemvvm.R;
import com.imran.room_databasemvvm.adapter.UsersAdapter;
import com.imran.room_databasemvvm.database.Users;
import com.imran.room_databasemvvm.viewmodel.ViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.ClickListner {
RecyclerView recyclerView;
UsersAdapter usersAdapter;
ViewModel viewModel;
FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel= ViewModelProviders.of(this).get(ViewModel.class);

        recyclerView=findViewById(R.id.recyclerview);
        floatingActionButton=findViewById(R.id.addnewuser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        usersAdapter=new UsersAdapter(this);

        viewModel.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                if (users.size()>0)
                {
                    usersAdapter.setData(users);
                    recyclerView.setAdapter(usersAdapter);
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adduser();
            }
        });
    }

    public void adduser()
    {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final View view=getLayoutInflater().inflate(R.layout.row_add,null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        Button adduser=view.findViewById(R.id.adduserbtn);
        final EditText euser=view.findViewById(R.id.etuser);
        TextView textView=view.findViewById(R.id.tvname);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (euser.getText()!=null)
                {
                    String username=euser.getText().toString().trim();
                    Users users=new Users();
                    users.setUsername(username);

                    viewModel.insertUser(users);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }

    public void updateuser(final Users users)
    {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final View view=getLayoutInflater().inflate(R.layout.row_add,null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        Button adduser=view.findViewById(R.id.adduserbtn);
        final EditText euser=view.findViewById(R.id.etuser);
        TextView textView=view.findViewById(R.id.tvdetails);

        textView.setText("Update details");
        adduser.setText("Update");

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (euser.getText()!=null)
                {
                    String username=euser.getText().toString().trim();

                    users.setUsername(username);

                    viewModel.updateUser(users);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }

    @Override
    public void updateClicked(Users users) {
     updateuser(users);
    }

    @Override
    public void deleteClicked(Users users)
    {
        viewModel.deleteUser(users);
    }
}