package com.example.vigsafeversion01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ReportResponse extends AppCompatActivity {

    TextView textResponse;
    EditText textSearch;
    DbManager obj = null;
    Button btnSearch, btnReturn;
    TableLayout table;
    String RESTAURANT_NAME = "MONTANA EXPRESS";

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_response);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        //textResponse = findViewById(R.id.textResponse);
        textSearch = findViewById(R.id.textSearch);
        btnSearch = findViewById(R.id.search_button);
        btnReturn = findViewById(R.id.return_button);
        table = findViewById(R.id.table_report);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        try {
                            generateImageView();
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.FileSavedToDownload), Toast.LENGTH_LONG).show();
                            //generatePDF(table);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.somthingWrong), Toast.LENGTH_LONG).show();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.somthingWrong), Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    try {
                        generateImageView();//generatePDF(table);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

                Intent intent = new Intent(ReportResponse.this, Activity_profile.class);
                startActivity(intent);
            }
        });

    }

    public void searchData() {

        obj = new DbManager(this);
        ArrayList<Measure> measures= obj.getData(textSearch.getText().toString());


        table.removeAllViews();

        for(Measure measure : measures) {
            TextView t1 = new TextView(this);
            TextView t2 = new TextView(this);
            TextView t3 = new TextView(this);

            t1.setText(measure.getDate());
            t2.setText(measure.getProductType());
            t3.setText(measure.getTemperature());

            TableRow row = new TableRow(this);
            row.addView(t1);
            row.addView(t2);
            row.addView(t3);

            table.addView(row);
        }

    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void generatePDF (TableLayout table) {
//
//        PdfDocument document = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(800, 1400, 1).create();
//        PdfDocument.Page page = document.startPage(pageInfo);
//
//        int measureWidth = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getWidth(), View.MeasureSpec.EXACTLY);
//        int measuredHeight = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getHeight(), View.MeasureSpec.EXACTLY);
//
//        table.measure(measureWidth, measuredHeight);
//        table.layout(0, 0, page.getCanvas().getWidth(), page.getCanvas().getHeight());
//
//        table.draw(page.getCanvas());
//
//        document.finishPage(page);
//
//        //String now = java.time.LocalDate.now().toString();
//        //String filepath = Environment.getExternalStorageDirectory().getPath() + "/report.pdf";
//        String filepath = "/storage/emulated/0/Download/report.pdf";
//        File myFile = new File(filepath);
//
//        try {
//            document.writeTo(new FileOutputStream(myFile));
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void generateImageView() throws IOException, DocumentException {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayoutCompat root = (LinearLayoutCompat) inflater.inflate(R.layout.activity_report_response, null); //LinearLayout is root view of my UI(xml) file.
        root.setDrawingCacheEnabled(true);
        View screenview = this.getWindow().findViewById(R.id.reportResponse);
        Bitmap screen= getBitmapFromView(screenview); // here give id of our root layout (here its my RelativeLayout's id)

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        screen.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();


        Document document = new Document();
        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(filepath, "myImage.pdf");
        OutputStream outputstream = new FileOutputStream(file);


        Image image = Image.getInstance(byteArray);

        int ratio = 50;
        int imagewidth = screenview.getWidth()/100*ratio;
        int imageHeight = screenview.getHeight()/100*ratio;

        image.scaleToFit(80, 300);
        PdfWriter.getInstance(document, outputstream);
        com.lowagie.text.Rectangle rectangule = new Rectangle(100, 300);
        document.setPageSize(rectangule);
        document.setMargins(10,2,2,2);

        document.open();
        document.add(image);
        document.close();

    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

}
