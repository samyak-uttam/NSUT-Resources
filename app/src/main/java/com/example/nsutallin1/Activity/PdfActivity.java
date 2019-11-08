package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nsutallin1.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        int index = getIntent().getIntExtra("index", 0);
        PDFView pdfView = findViewById(R.id.pdf_viewer);

        pdfView.fromFile(getExternalFilesDir(null).listFiles()[index])
                .enableSwipe(true)
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}
