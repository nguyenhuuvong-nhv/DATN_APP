package com.example.doantn.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.doantn.R;

import org.bson.Document;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class Login extends AppCompatActivity {
    Button btlogin;
    EditText txUser, txtPass;
    String Appid = "doantn2021-lztvd";
    private App app;
    public User user;
    MongoClient mongoClient;
    public MongoDatabase mongoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btlogin = (Button) findViewById(R.id.bt_sigin);
        txUser = (EditText) findViewById(R.id.editTextTextEmailAddress);
        txtPass = (EditText) findViewById(R.id.editTextTextPassword);
        int permission_internet = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        if (permission_internet != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        }

        Realm.init(this);

        app = new App(new AppConfiguration.Builder(Appid).build());
        Credentials credentials = Credentials.anonymous();
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess())
                {
                    user = app.currentUser();

                    Log.v("User","Logged In Successfully");

                }
                else
                {
                    Log.v("User","Failed to Login");
                }
            }
        });


        btlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mongoClient = user.getMongoClient("mongodb-atlas");
                mongoDatabase = mongoClient.getDatabase("DoAnTN");
                MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("TaiKhoan");
                Document queryFilter = null;
                try {
                    queryFilter = new Document().append("UserName", txUser.getText().toString()).append("MatKhau",convertHashToString( txtPass.getText().toString()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                mongoCollection.findOne(queryFilter).getAsync(result -> {
                    try {
                        if (result.isSuccess()) {
                            Document resultdata = result.get();
                            String tmp = resultdata.getString("HoTen");
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_LONG).show();
                            doOpenMain();


                        } else {
                            Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu ! ", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu !", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
    }

    public void doOpenMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                Manifest.permission.SEND_SMS, Manifest.permission.CAMERA}, 1);
    }
    private String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
