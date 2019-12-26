package com.educational.nsutresources.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.educational.nsutresources.R;

public class AboutDevelopersActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView noteTV;
    private String note;
    private ImageView samyakLinkdinImageView;
    private ImageView samyakFacebookImageView;
    private ImageView samyakInstagramImageView;

    private ImageView ankitLinkdinImageView;
    private ImageView ankitFacebookImageView;
    private ImageView ankitInstagramImageView;

    private CardView contributeCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_developers);

        getSupportActionBar().setTitle("ABOUT US");

        noteTV = findViewById(R.id.note);
        note = "Note: If you find any content unsuitable for the app, please contact us here.";
        SpannableString ss = new SpannableString(note);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String[] TO = {"ankit.pro.132000@gmail.com", "samyakuttam@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Complaint regarding the resources");
                emailIntent.setType("message/rfc822");
                startActivity(emailIntent);
            }
        };

        ss.setSpan(clickableSpan, 72, 76, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        noteTV.setText(ss);
        noteTV.setMovementMethod(LinkMovementMethod.getInstance());
        noteTV.setHighlightColor(Color.TRANSPARENT);

        samyakLinkdinImageView = findViewById(R.id.samyak_linkdin);
        samyakFacebookImageView = findViewById(R.id.samyak_facebook);
        samyakInstagramImageView = findViewById(R.id.samyak_instagram);


        ankitLinkdinImageView = findViewById(R.id.ankit_linkdin);
        ankitFacebookImageView = findViewById(R.id.ankit_facebook);
        ankitInstagramImageView = findViewById(R.id.ankit_instagram);

        contributeCardView = findViewById(R.id.contribue_card);

        samyakLinkdinImageView.setOnClickListener(this);
        samyakFacebookImageView.setOnClickListener(this);
        samyakInstagramImageView.setOnClickListener(this);

        ankitLinkdinImageView.setOnClickListener(this);
        ankitFacebookImageView.setOnClickListener(this);
        ankitInstagramImageView.setOnClickListener(this);

        contributeCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.samyak_linkdin:
                String linkdinUrl = "https://www.linkedin.com/in/samyak-uttam-364816194/";
                Intent linkdinintent = new Intent(Intent.ACTION_VIEW);
                linkdinintent.setData(Uri.parse(linkdinUrl));
                startActivity(linkdinintent);
                break;

            case R.id.samyak_facebook:
                String facebookUrl = "https://www.facebook.com/samyak.uttam";
                Intent facebookintent = new Intent(Intent.ACTION_VIEW);
                facebookintent.setData(Uri.parse(facebookUrl));
                startActivity(facebookintent);
                break;

            case R.id.samyak_instagram:
                String instaUrl = "https://www.instagram.com/samyak_uttam/?hl=en";
                Intent instaintent = new Intent(Intent.ACTION_VIEW);
                instaintent.setData(Uri.parse(instaUrl));
                startActivity(instaintent);
                break;


            case R.id.ankit_linkdin:
                String linkdinUrlA = "https://www.linkedin.com/in/ankit-kumar-mishra-68873b17a/";
                Intent linkdinintentA = new Intent(Intent.ACTION_VIEW);
                linkdinintentA.setData(Uri.parse(linkdinUrlA));
                startActivity(linkdinintentA);
                break;


            case R.id.ankit_facebook:
                String facebookUrlA = "https://www.facebook.com/profile.php?id=100005508943959";
                Intent facebookintentA = new Intent(Intent.ACTION_VIEW);
                facebookintentA.setData(Uri.parse(facebookUrlA));
                startActivity(facebookintentA);
                break;

            case R.id.ankit_instagram:
                String instaUrlA = "https://www.instagram.com/ankit132000/?hl=en";
                Intent instaintentA = new Intent(Intent.ACTION_VIEW);
                instaintentA.setData(Uri.parse(instaUrlA));
                startActivity(instaintentA);
                break;

            case R.id.contribue_card:
                String[] TO = {"ankit.pro.132000@gmail.com", "samyakuttam@gmail.com"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Material for NSUT All In 1");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I would like to add the following resources:");
                emailIntent.setType("message/rfc822");

                startActivity(emailIntent);
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}