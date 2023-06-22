package com.example.world.gameActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.world.AbstractActivity;
import com.example.world.R;
import com.example.world.Screen;

public class WaitingScreenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_weiting_screen, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.loading);
        final int height = AbstractActivity.getActualActivity().getDrawable(R.drawable.loading2).getIntrinsicHeight();
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(height, height));
        rotateImage(imageView);
        return root;
    }

    public volatile boolean stopRotate = false;

    private void rotateImage(ImageView imageView) {
        new Thread(() -> {
            float angle = 0;
            while (!stopRotate) {
                angle += 0.1f;
                final Matrix matrix = new Matrix();
                final float angle2 = angle;
                
                new Thread(() -> {
                    AbstractActivity.getActualActivity().runOnUiThread(() -> {
                        imageView.setScaleType(ImageView.ScaleType.MATRIX); // required
                        matrix.postRotate((float) angle2, imageView.getWidth() / 2,
                        imageView.getHeight() / 2);
                        imageView.setImageMatrix(matrix);
                    });
                }).start();

                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

    }
}
