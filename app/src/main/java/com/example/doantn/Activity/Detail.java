package com.example.doantn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.doantn.R;

import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class Detail extends AppCompatActivity {
    TextView  name, maPB, nguoiQL, trangThai,Serial,NhanHieu,CPU,Ram,BoNho,CongSuat;
    String id_PB = null;
    EditText mota;
    Button baohong,sudung;
    private App app;
    String Appid = "doantn2021-lztvd";
    ArrayList<String> strings = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        name = findViewById(R.id.txt_name);
        maPB = findViewById(R.id.tx_MaPB);
        nguoiQL = findViewById(R.id.txt_NguoiQL);
        mota = findViewById(R.id.txt_mota);
        baohong = findViewById(R.id.bt_baohong);
        trangThai = findViewById(R.id.txt_trangthai);
        Serial = findViewById(R.id.txt_Serial);
        NhanHieu = findViewById(R.id.txt_nhanHieu);
        CPU = findViewById(R.id.txt_CPU);
        Ram = findViewById(R.id.txt_Ram);
        BoNho = findViewById(R.id.txt_BoNho);
        CongSuat = findViewById(R.id.txt_CongSuat);
        sudung =findViewById(R.id.bt_sudung);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chi tiết thiết bị");


        Intent intent = getIntent();

        if (intent != null) {
            app = new App(new AppConfiguration.Builder(Appid).build());
            Credentials credentials = Credentials.anonymous();
            app.loginAsync(Credentials.anonymous(), new App.Callback<User>() {
                @Override
                public void onResult(App.Result<User> result) {
                    if (result.isSuccess()) {
                        Log.v("User", "Logged In Successfully");
                    } else {
                        Log.v("User", "Failed to Login");
                    }
                }
            });
            User user = app.currentUser();
            MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("DoAnTN");

            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("ThietBi");


            Document queryFilter = new Document().append("_id", intent.getStringExtra("id"));


            mongoCollection.findOne(queryFilter).getAsync(result -> {
                try {
                    if (result.isSuccess()) {
                        Document resultdata = result.get();
                        Document chitiet = (Document) resultdata.get("ChiTietTB");

                        Serial.setText(chitiet.getString("Serial"));
                        NhanHieu.setText(chitiet.getString("NhanHieu"));
                        CPU.setText(chitiet.getString("CPU"));
                        Ram.setText(chitiet.getString("Ram")+" GB");
                        BoNho.setText(chitiet.getString("BoNho")+ " GB");
                        CongSuat.setText(chitiet.getString("CongSuat")+" V");


                        name.setText(resultdata.getString("TenTB"));
                        if (resultdata.getInteger("TrangThai") == 0) {
                            trangThai.setText("Hoạt động");
                            sudung.setVisibility(View.GONE);
                        } else if (resultdata.getInteger("TrangThai") == 1) {
                            trangThai.setText("Hư hỏng");
                            mota.setVisibility(View.GONE);
                            baohong.setVisibility(View.GONE);
                        } else if (resultdata.getInteger("TrangThai") == 2) {
                            trangThai.setText("Đang sữa chữa");
                            mota.setVisibility(View.GONE);
                            baohong.setVisibility(View.GONE);
                        } else if (resultdata.getInteger("TrangThai") == 3) {
                            trangThai.setText("Đang bảo hành");
                            mota.setVisibility(View.GONE);
                            baohong.setVisibility(View.GONE);
                        } else if (resultdata.getInteger("TrangThai") == 4) {
                            trangThai.setText("Đã thanh lý");
                            mota.setVisibility(View.GONE);
                            baohong.setVisibility(View.GONE);
                            sudung.setVisibility(View.GONE);
                        }


                        //Filter Phòng ban
                        MongoCollection<Document> PhongBan = mongoDatabase.getCollection("PhongBan");
                        Document queryFilter1 = new Document().append("_id", resultdata.getString("MaPB"));
                        PhongBan.findOne(queryFilter1).getAsync(result1 -> {
                            try {
                                if (result1.isSuccess()) {
                                    Document resultdata1 = result1.get();
                                    maPB.setText(resultdata1.getString("TenPB"));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code! ", Toast.LENGTH_LONG).show();
                                    doOpenMain();
                                }
                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code !", Toast.LENGTH_LONG).show();
                                doOpenMain();
                            }

                        });

                        //Filter Người quản lý

                        MongoCollection<Document> Nql = mongoDatabase.getCollection("TaiKhoan");
                        Document queryFilter2 = new Document().append("_id", resultdata.getString("NguoiQuanLy"));
                        Nql.findOne(queryFilter2).getAsync(result2 -> {
                            try {
                                if (result2.isSuccess()) {
                                    Document resultdata2 = result2.get();
                                    nguoiQL.setText(resultdata2.getString("HoTen"));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code! ", Toast.LENGTH_LONG).show();
                                    doOpenMain();
                                }
                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code !", Toast.LENGTH_LONG).show();
                                doOpenMain();
                            }

                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code ! ", Toast.LENGTH_LONG).show();
                        doOpenMain();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Không tìm thấy mã QR-code !", Toast.LENGTH_LONG).show();
                    doOpenMain();
                }

            });


            baohong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String date = sdf.format(new Date());
                    User user = app.currentUser();
                    MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("DoAnTN");
                    MongoCollection<Document> collection_TBHH = mongoDatabase.getCollection("ThietBiHuHong");
                    Document document = new Document().append("_id", getRandomString(24)).append("MaTB", intent.getStringExtra("id")).append("MoTa", mota.getText().toString()).append("ThoiGian", date.toString()).append("TrangThai", 0);

                    collection_TBHH.insertOne(document).getAsync(result -> {
                        if (result.isSuccess()) {
                            Toast.makeText(getApplicationContext(), "Báo hỏng thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Log.v("adding", "result failed" + result.getError().toString());
                            Toast.makeText(getApplicationContext(), "Báo hỏng thất bại", Toast.LENGTH_LONG).show();
                        }
                    });

                    MongoCollection<Document> collection_TB = mongoDatabase.getCollection("ThietBi");
                    Document queryFilter = new Document().append("_id", intent.getStringExtra("id"));
                    RealmResultTask<MongoCursor<Document>> findTask = collection_TB.find(queryFilter).iterator();

                    findTask.getAsync(task -> {
                        if (task.isSuccess()) {
                            MongoCursor<Document> results = task.get();

                            if (results.hasNext()) {
                                Document result = results.next();

                                result.append("TrangThai", 1);
                                collection_TB.updateOne(queryFilter, result).getAsync(result1 -> {
                                    if (result1.isSuccess()) {
                                        doOpenMain();
                                    } else {
                                        doOpenMain();
                                    }

                                });
                            }
                        }
                        });
                }
            });
            sudung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = app.currentUser();
                    MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("DoAnTN");

                    MongoCollection<Document> collection_TB = mongoDatabase.getCollection("ThietBi");
                    Document queryFilter = new Document().append("_id", intent.getStringExtra("id"));
                    RealmResultTask<MongoCursor<Document>> findTask = collection_TB.find(queryFilter).iterator();

                    findTask.getAsync(task -> {
                        if (task.isSuccess()) {
                            MongoCursor<Document> results = task.get();

                            if (results.hasNext()) {
                                Document result = results.next();

                                result.append("TrangThai", 0);
                                collection_TB.updateOne(queryFilter, result).getAsync(result1 -> {
                                    if (result1.isSuccess()) {
                                        Log.v("UpdateFunction", "Updated Data");

                                    } else {
                                        Log.v("UpdateFunction", "Error" + result1.getError().toString());
                                    }

                                });
                            }
                        }
                    });

                    MongoCollection<Document> collection_TBHH = mongoDatabase.getCollection("ThietBiHuHong");
                    Document queryFilter1 = new Document().append("MaTB", intent.getStringExtra("id"));
                    RealmResultTask<MongoCursor<Document>> findTask1 = collection_TBHH.find(queryFilter1).iterator();

                    findTask1.getAsync(task -> {
                        if (task.isSuccess()) {
                            MongoCursor<Document> results = task.get();

                            if (results.hasNext()) {
                                Document result = results.next();

                                result.append("TrangThai", 4);
                                collection_TBHH.updateOne(queryFilter1, result).getAsync(result1 -> {
                                    if (result1.isSuccess()) {
                                        Toast.makeText(getApplicationContext(), "Cập nhật trạng thái thành công", Toast.LENGTH_LONG).show();
                                        doOpenMain();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Cập nhật trạng thái thất bại", Toast.LENGTH_LONG).show();
                                        doOpenMain();
                                    }

                                });
                            }
                        }
                    });
                }
            });

        }
    }

    String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

    public String getRandomString(int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // nút back
                doOpenMain();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    public void doOpenMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
