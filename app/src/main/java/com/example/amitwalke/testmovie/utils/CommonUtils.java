package com.example.amitwalke.testmovie.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.amitwalke.testmovie.R;


public class CommonUtils {

    private static CommonUtils commonUtils;

    public static CommonUtils getInstance() {

        if (commonUtils == null) {

            commonUtils = new CommonUtils();
        }

        return commonUtils;
    }


    public void downloadMyImageUsingUrl(Context context, String id, ImageView imageView) {

        String imageUrl = AppConstants.imgBaseUrl+id;

        Glide.with(context).load(imageUrl).apply(new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.img_placholder)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .dontAnimate()
                .dontTransform())
                .into(imageView);
    }


}
