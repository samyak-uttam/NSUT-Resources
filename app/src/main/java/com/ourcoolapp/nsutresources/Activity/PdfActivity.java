package com.ourcoolapp.nsutresources.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ourcoolapp.nsutresources.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        String name = "." + getIntent().getStringExtra("name");
        PDFView pdfView = findViewById(R.id.pdf_viewer);
        int i = 0;
        File[] listFile = getExternalFilesDir(null).listFiles();
        for (i = 0; i < listFile.length; i++) {
            if (listFile[i].getName().split("&")[0].equals(name)) {
                break;
            }
        }

        pdfView.fromFile(listFile[i])
                .enableSwipe(true)
                .defaultPage(0)
                .password("samyak")
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

    }
}