package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nsutallin1.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        String name = getIntent().getStringExtra("name");
        PDFView pdfView = findViewById(R.id.pdf_viewer);
        int i = 0;
        File listFile[] = getExternalFilesDir(null).listFiles();
        for(i = 0; i < listFile.length; i++) {
            if(listFile[i].getName().split("&")[0].equals(name)) {
                break;
            }
        }
        pdfView.fromFile(listFile[i])
                .enableSwipe(true)
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}