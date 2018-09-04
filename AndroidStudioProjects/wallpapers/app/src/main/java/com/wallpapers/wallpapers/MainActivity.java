package com.wallpapers.wallpapers;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    GridView myGridView;
    ImageView myCurrentWallpaper;
    Drawable myDrawable;
    WallpaperManager myWallManager;

    Integer[] imgArray={
            R.drawable.wall1,R.drawable.wall2,R.drawable.wall3
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGridView=findViewById(R.id.myGridView);
        myGridView.setAdapter(new ImageAdapter(getApplicationContext()));

        myCurrentWallpaper=findViewById(R.id.myImageView);
        myCurrentWallpaper.buildDrawingCache();
        Bitmap bmap = myCurrentWallpaper.getDrawingCache();

        updateMyWallpaper(bmap);
    }

    private void updateMyWallpaper(Bitmap bmap){
        myWallManager=WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallManager.setBitmap(bmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        //myDrawable=myWallManager.getDrawable();
        //myCurrentWallpaper.setImageDrawable(myDrawable);
    }

    public class ImageAdapter extends BaseAdapter{
        Context myContext;
        public ImageAdapter(Context applicationContext) {
            myContext=applicationContext;
        }

        @Override
        public int getCount() {
            return imgArray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            ImageView GridImageView;
            if(view==null){
                GridImageView =new ImageView(myContext);
                GridImageView.setLayoutParams(new GridView.LayoutParams(512,512));
                GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else{
                GridImageView =(ImageView)view;
            }

            GridImageView.setImageResource(imgArray[i]);

            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
          //          try {
                        myCurrentWallpaper=findViewById(imgArray[i]);
                        myCurrentWallpaper.buildDrawingCache();
                        Bitmap bmap = myCurrentWallpaper.getDrawingCache();
                        //myWallManager.setResource(imgArray[i]);
            //        } catch (IOException e) {
              //          e.printStackTrace();
//                    }
                    updateMyWallpaper(bmap);
                }
            });

            return GridImageView;
        }
    }
}

