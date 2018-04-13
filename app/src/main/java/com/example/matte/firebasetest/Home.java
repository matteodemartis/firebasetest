package com.example.matte.firebasetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //-----------------------------
        final List<Product> mProductList;

        ListView list = (ListView)findViewById(R.id.nameList);
        ProductListAdapter adapter;
        mProductList = new ArrayList<>();
        mProductList.add(new Product(1,"Samsung",456,"New gen tv"));
        mProductList.add(new Product(2,"Lg",375,"Old but good"));
        mProductList.add(new Product(3,"Toshiba",873,"3D without glasses"));
        mProductList.add(new Product(4,"Sony",503,"La tv dei sogni"));

        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You chose " + mProductList.get(i).getName(), Toast.LENGTH_SHORT).show();

            }
        });
        //-----------------------------
        mAuth = FirebaseAuth.getInstance();
        Button signup = (Button)findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText)findViewById(R.id.editText2)).getText().toString();
                String password = ((EditText)findViewById(R.id.editText3)).getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Home.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user.
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Home.this,"User:" + user.getEmail().toString() + " created", Toast.LENGTH_SHORT).show();
                                } else Toast.makeText(Home.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                                // ...
                            }
                        });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            Toast.makeText(this,"User"+currentUser.getEmail().toString(),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No user authenticated",Toast.LENGTH_SHORT).show();
    }

}
