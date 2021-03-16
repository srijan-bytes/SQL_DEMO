package com.example.sqldatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //creating references
    EditText et_name,et_age;
    Switch ActiveCustomer;
    Button btn_add, btn_viewAll;
    ListView lv_customerList;

    ArrayAdapter customerArray;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        ActiveCustomer=findViewById(R.id.sw_active);
        btn_add=findViewById(R.id.btn_add);
        btn_viewAll=findViewById(R.id.btn_viewAll);
        lv_customerList=findViewById(R.id.lv_customerList);
        //view on create
        databaseHelper=new DatabaseHelper(MainActivity.this);
        viewOnList(databaseHelper);
        //listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try
                {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), ActiveCustomer.isChecked(), Integer.parseInt(et_age.getText().toString()));
                    //Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    boolean success = databaseHelper.addOne(customerModel);
                    //displaying list
                    viewOnList(databaseHelper);
                    if (success)
                        Toast.makeText(MainActivity.this, "Data Added SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Data NOT Added", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Please input data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewOnList(databaseHelper);
                //Toast.makeText(MainActivity.this, "VIEW ALL button", Toast.LENGTH_SHORT).show();
            }
        });
        //deleting an item from list
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel deletecustomer=(CustomerModel)parent.getItemAtPosition(position);
                boolean check=databaseHelper.deleteOne(deletecustomer);
                if(!check)
                {
                    Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Item NOT Deleted", Toast.LENGTH_SHORT).show();
                }
                viewOnList(databaseHelper);

            }
        });

    }

    private void viewOnList(DatabaseHelper databaseHelper2) {
        customerArray = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper2.getAll());
        lv_customerList.setAdapter(customerArray);
    }


}