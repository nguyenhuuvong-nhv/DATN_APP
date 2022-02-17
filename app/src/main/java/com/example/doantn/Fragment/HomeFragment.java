package com.example.doantn.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doantn.Activity.Login;
import com.example.doantn.Activity.Scanner;
import com.example.doantn.R;

import org.bson.Document;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class HomeFragment extends Fragment {

    ImageButton bt_scan;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.fragment_home, container, false);
        bt_scan = root.findViewById(R.id.bt_scan);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenScan();
            }
        });

        return  root;
    }
    public void doOpenScan() {
        Intent myIntent = new Intent(getActivity(), Scanner.class);
        startActivity(myIntent);
    }
}