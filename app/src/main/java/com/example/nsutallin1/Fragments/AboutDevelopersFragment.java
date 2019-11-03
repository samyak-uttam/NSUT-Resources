package com.example.nsutallin1.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.nsutallin1.R;

public class AboutDevelopersFragment extends Fragment implements View.OnClickListener {


    ImageView samyakLinkdinImageView;
    ImageView samyakFacebookImageView;
    ImageView samyakInstagramImageView;

    ImageView ankitLinkdinImageView;
    ImageView ankitFacebookImageView;
    ImageView ankitInstagramImageView;

    CardView contributeCardView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String url = "https://api.whatsapp.com/send?phone=" + "+91 9868771195";
        View rootView = inflater.inflate(R.layout.about_developers, container, false);
        samyakLinkdinImageView = rootView.findViewById(R.id.samyak_linkdin);
        samyakFacebookImageView = rootView.findViewById(R.id.samyak_facebook);
        samyakInstagramImageView = rootView.findViewById(R.id.samyak_instagram);

        ankitLinkdinImageView = rootView.findViewById(R.id.ankit_linkdin);
        ankitFacebookImageView = rootView.findViewById(R.id.ankit_facebook);
        ankitInstagramImageView = rootView.findViewById(R.id.ankit_instagram);

        contributeCardView=rootView.findViewById(R.id.contribue_card);

        samyakLinkdinImageView.setOnClickListener(this);
        samyakFacebookImageView.setOnClickListener(this);
        samyakInstagramImageView.setOnClickListener(this);

        ankitLinkdinImageView.setOnClickListener(this);
        ankitFacebookImageView.setOnClickListener(this);
        ankitInstagramImageView.setOnClickListener(this);

        contributeCardView.setOnClickListener(this);


        return rootView;


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
                Log.i("Send email", "");
                String[] TO = {"ankit.pro.132000@gmail.com","samyakuttam@gmail.com"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Material for NSUT All In 1");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I would like to add the following resources:");
                emailIntent.setType("message/rfc822");


                try {
                    startActivity(Intent.createChooser(emailIntent, "Send Resources"));
                    Log.i("Finished sending email", "done");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        }


    }

