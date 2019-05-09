package mx.edu.ittepic.frameanimationefren;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private AnimationDrawable CactusAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView =(ImageView) findViewById(R.id.animation);
        if (imageView == null) throw new AssertionError();

        imageView.setVisibility(View.INVISIBLE);
        imageView.setBackgroundResource(R.drawable.frame_animation_list);

        CactusAnimation = (AnimationDrawable) imageView.getBackground();
        CactusAnimation.setOneShot(true);
    }

    public void onStartBtnClick(View v) {
        imageView.setVisibility(View.VISIBLE);
        if (CactusAnimation.isRunning()) {
            CactusAnimation.stop();
        }
        CactusAnimation.start();
    }
    public void onStopBtnClick(View v) {
        CactusAnimation.stop();
    }
}
