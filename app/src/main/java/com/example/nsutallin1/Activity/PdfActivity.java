package com.example.nsutallin1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.nsutallin1.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class PdfActivity extends AppCompatActivity {

    private File decrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        String name = getIntent().getStringExtra("name");
        PDFView pdfView = findViewById(R.id.pdf_viewer);
        int i = 0;
        File[] listFile = getExternalFilesDir(null).listFiles();
        for (i = 0; i < listFile.length; i++) {
            if (listFile[i].getName().split("&")[0].equals(name)) {
                break;
            }
        }

        decrypted = new File(getExternalFilesDir(null),"temp.decrypted");

        try {
            FileInputStream in = new FileInputStream(listFile[i]);
            FileOutputStream out = new FileOutputStream(decrypted);

            decrypt(in, out);
            pdfView.fromFile(decrypted)
                    .enableSwipe(true)
                    .defaultPage(0)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    static void decrypt(FileInputStream in, FileOutputStream out) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        SecretKeySpec sks = new SecretKeySpec("MyDifficultPassw".getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        CipherInputStream cis = new CipherInputStream(in, cipher);
        int b;
        byte[] d = new byte[10240];
        while((b = cis.read(d)) != -1) {
            out.write(d, 0, b);
        }
        out.flush();
        out.close();
        cis.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        decrypted.delete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        decrypted.delete();
    }
}