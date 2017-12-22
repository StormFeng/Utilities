package com.nof.utilities.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nof.utilities.R;

/**
 * Created by Administrator on 2017/12/22.
 */

public class StarsMenuActivity extends Activity implements View.OnClickListener {

    int[] ids = {R.id.ive, R.id.iva,R.id.ivb,R.id.ivc,R.id.ivd};
    ImageView[] ivs = new ImageView[ids.length];
    boolean isOpen = false;

    int[] idsCopy = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5};
    ImageView[] ivsCopy = new ImageView[idsCopy.length];
    boolean isOpenCopy = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starsmenu);
        for (int i = 0; i < ids.length; i++) {
            ivs[i] = findViewById(ids[i]);
            ivs[i].setOnClickListener(this);
        }

        for (int i = 0; i < idsCopy.length; i++) {
            ivsCopy[i] = findViewById(idsCopy[i]);
            ivsCopy[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ive:
//                expandStarsMenu(isOpen);
                expandLinesMenu(isOpen);
                break;
            case R.id.btn_showflodmenu:
                expandFoldMenu(isOpenCopy);
                break;
            default:
                Toast.makeText(this,v.getId()+"",Toast.LENGTH_SHORT).show();
                break;


        }
    }

    /**
     * 星星菜单
     * @param b
     */
    private void expandStarsMenu(boolean b){
        if (!b) {
            float defRadius = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 100, this.getResources()
                            .getDisplayMetrics());
            double angle = 30f;
            for (int i = 1; i < 5; i++) {
                double sin = Math.sin(Math.toRadians(angle * (i - 1)));
                double cos = Math.cos(Math.toRadians(angle * (i - 1)));
                int x = (int) (defRadius * sin);
                int y = (int) (defRadius * cos);

                PropertyValuesHolder animator1=PropertyValuesHolder.ofFloat("X", 0F,x);
                PropertyValuesHolder animator2=PropertyValuesHolder.ofFloat("Y", 0F,y);
                ObjectAnimator.ofPropertyValuesHolder(ivs[i], animator1, animator2)
                        .setDuration(100*i).start();
            }
        }
        else {
            for(int i=1;i<5;i++) {
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X", ivs[i].getX(),
                        0F);
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("Y", ivs[i].getY(),
                        0F);
                ObjectAnimator.ofPropertyValuesHolder(ivs[i], p1, p2)
                        .setDuration(100*i).start();
            }
        }
        isOpen = !isOpen;
    }

    /**
     * 线型菜单
     * @param b
     */
    private void expandLinesMenu(boolean b){
        if(!b){
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float interval = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,metrics);
            for (int i = 1; i < 5; i++) {
                int x = (int) (ivs[5-i].getWidth() + interval) * (5-i);
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X",x);
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("rotation",360);
                ObjectAnimator.ofPropertyValuesHolder(ivs[5-i],p1,p2).setDuration(300*i).start();
            }
        }else{
            for (int i = 1; i < 5; i++) {
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("rotation",-360);
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X",0);
                ObjectAnimator.ofPropertyValuesHolder(ivs[i],p1,p2).setDuration(300*i).start();
            }
        }
        isOpen = !isOpen;
    }

    private void expandFoldMenu(boolean b){
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        if(!b){
            for (int i = 0; i < 5; i++) {
                int x = ivsCopy[i].getWidth();
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X",width - x);
                ObjectAnimator.ofPropertyValuesHolder(ivsCopy[i],p1).setDuration(200*i).start();
            }
        }else{
            for (int i = 0; i < 5; i++) {
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X",width);
                ObjectAnimator.ofPropertyValuesHolder(ivsCopy[4-i],p1).setDuration(200*i).start();
            }
        }
        isOpenCopy = !isOpenCopy;
    }
}
