package com.example.android.fifa_fixtures;

/**
 * Created by hp on 10-06-2018.
 */

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.fifa_fixtures.R;

import static android.support.v4.app.ActivityCompat.startActivityForResult;


public class Dialog_Box {

    public static void showDailogBox(final AppCompatActivity cmp, final int CAMERA_REQUEST, final int GALLERY_REQUEST) {
        final Dialog d = new Dialog(cmp);
        View v = LayoutInflater.from(cmp).inflate(R.layout.dailog_box, null);
        TextView camera_image = v.findViewById(R.id.camera_title);
        TextView cancel = v.findViewById(R.id.cancel_title);
        TextView gallery_image = v.findViewById(R.id.gallery_title);
        d.setCancelable(false);
        camera_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.checkPermission(cmp)) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cmp, cameraIntent, CAMERA_REQUEST,null);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        gallery_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.checkPermission(cmp)) {
                    Intent galleryIntent = new Intent();
                    galleryIntent.setType("image/*");
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(cmp, galleryIntent, GALLERY_REQUEST, null);
                }
            }
        });
        d.setContentView(v);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        d.getWindow().setAttributes(lp);
        d.show();
    }


}
