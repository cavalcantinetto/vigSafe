package com.example.vigsafeversion01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


// ABANDONEI ESSA FEATURE POR ENQUANTO QUANDO VOLTAR, TENTAR APAGAR DIRETAMENTE NO CARDVIEW.


public class DeleteProducts extends AppCompatActivity {

    EditText productType, productDescription;
    Button searchProduct, deleteProduct;
    Cursor cursor;
    TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_products);
        DbManager db = new DbManager(this);

        productType = findViewById(R.id.deleteProductType);
        productDescription = findViewById(R.id.insertProductDescription);
        deleteProduct = findViewById(R.id.deleteproductbutton);
        searchProduct = findViewById(R.id.searchproductbutton);


//        deleteProduct.setOnClickListener(v -> {
//          Boolean result = db.removeRecordFood(productType.getText().toString());
//          if (result) {
//              String strSuccess = getResources().getString(R.string.deleteMenuSuccess);
//              Toast.makeText(getApplicationContext(), strSuccess, Toast.LENGTH_LONG).show();
//          } else {
//              String strFailed = getResources().getString(R.string.deleteMenuFail);
//              Toast.makeText(getApplicationContext(), strFailed, Toast.LENGTH_LONG).show();
//          }
//        });
//
//        searchProduct.setOnClickListener(v -> {
//
//            if (productType != null && productDescription != null) {
//                cursor = db.searchProduct(productType.getText().toString().trim().toUpperCase(), productDescription.getText().toString().trim().toUpperCase());
//                showItensToBeDeleted(cursor);
//            } else if (productType != null) {
//                cursor = db.searchProduct(productType.getText().toString().trim().toUpperCase(), null);
//                showItensToBeDeleted(cursor);
//            } else if (productDescription != null) {
//                cursor = db.searchProduct(null, productDescription.getText().toString().trim().toUpperCase());
//            } else {
//                Toast.makeText(getApplicationContext(), "At least one argument must be filled", Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//        }

//    public void showItensToBeDeleted(Cursor list) {
//
//        TableLayout table = (TableLayout) findViewById(R.id.table_menu_delete);
//
//        //String valueteste = cursor.getColumnName(2);
//        if (list != null) {
//            while (list.moveToNext()) {
//                TextView innerproductType = new TextView(getApplicationContext());
//                TextView innerproductDescription = new TextView(getApplicationContext());
//
//                innerproductType.setText(list.getString(2));
//                innerproductDescription.setText(list.getString(3));
//
//                table.removeAllViews();
//
//                TableRow row = new TableRow(getApplicationContext());
//                row.addView(innerproductType);
//                row.addView(innerproductDescription);
//                table.addView(row);
//            }
//
//        } else {
//            Toast.makeText(getApplicationContext(), "Product type or Description not found.", Toast.LENGTH_LONG).show();
//        }
//    }

    }
}