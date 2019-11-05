package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nsutallin1.R;
import com.pdfview.PDFView;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        int index = getIntent().getIntExtra("index", 0);
        PDFView view = findViewById(R.id.pdf_view);
        view.fromFile(getExternalFilesDir(null).listFiles()[index])
                .show();
    }
}
