package com.example.elashry.sql_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    DB_Helper db=new DB_Helper(this);
    @BindView(R.id.edtemail)EditText email;
    @BindView(R.id.edtname) EditText name;
    @BindView(R.id.edtid) EditText id;

    @BindView(R.id.list_data)ListView list;
    @BindView(R.id.btnadd)Button add;
    @BindView(R.id.btnupdate)Button update;
    @BindView(R.id.btndelete )Button delete;
//EditText name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // email= (EditText) findViewById(R.id.edtemail);
       //  name= (EditText) findViewById(R.id.edtemail);

        ButterKnife.bind(this);
        read_data();
    }

    public void btn_add(View view) {

        String mname=name.getText().toString();
        String memail=email.getText().toString();

      boolean result=  db.insert(mname,memail);
        if (result== true){
            Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            read_data();
        }else{
            Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
        }
    }
    public void read_data() {
        ArrayList<String> arraylist =db.getdata();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arraylist);

        list.setAdapter(adapter);

    }
    public void btn_update(View view) {
        String mname=name.getText().toString();
        String memail=email.getText().toString();
        String Id=id.getText().toString();

        boolean result=  db.update_data(Id,mname,memail);
        if (result== true){
            Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            id.setText("");

            read_data();
        }else{
            Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_delete(View view) {
        String Id=id.getText().toString();
        Integer result=db.delete(Id);
        if (result>0){
            Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
            read_data();

        }else{
            Toast.makeText(this, "error ", Toast.LENGTH_SHORT).show();
        }
    }
}
