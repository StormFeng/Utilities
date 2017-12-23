package com.nof.utilities.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.nof.utilities.R;

/**
 * Created by Administrator on 2017/12/22.
 */

public class StarsMenuActivity extends Activity implements View.OnClickListener {

    private int[] mStarsIds = {R.id.ive, R.id.iva,R.id.ivb,R.id.ivc,R.id.ivd};
    private ImageView[] mStarsIvs = new ImageView[mStarsIds.length];
    private boolean isStarsMenuOpen = false;

    private int[] mFoldIds = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5};
    private ImageView[] mFoldIvs = new ImageView[mFoldIds.length];
    private boolean isFoldMenuOpen = false;

    private static final int DUTATION = 200;
    private static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final float STARS_RADIUS = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 100, Resources.getSystem().getDisplayMetrics());
    private static final float ITEM_INTERVAL = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10,Resources.getSystem().getDisplayMetrics());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starsmenu);
        for (int i = 0; i < mStarsIds.length; i++) {
            mStarsIvs[i] = findViewById(mStarsIds[i]);
            mStarsIvs[i].setOnClickListener(this);
        }

        for (int i = 0; i < mFoldIds.length; i++) {
            mFoldIvs[i] = findViewById(mFoldIds[i]);
            mFoldIvs[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ive:
//                expandStarsMenu(isStarsMenuOpen);
                expandLinesMenu(isStarsMenuOpen);
                break;
            case R.id.btn_showflodmenu:
                expandFoldMenu(isFoldMenuOpen);
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
            double angle = 30f;
            for (int i = 1; i < mStarsIds.length; i++) {
                double sin = Math.sin(Math.toRadians(angle * (i - 1)));
                double cos = Math.cos(Math.toRadians(angle * (i - 1)));
                int x = (int) (STARS_RADIUS * sin);
                int y = (int) (STARS_RADIUS * cos);

                PropertyValuesHolder pX=PropertyValuesHolder.ofFloat("X", mStarsIvs[i].getX(),x);
                PropertyValuesHolder pY=PropertyValuesHolder.ofFloat("Y", mStarsIvs[i].getY(),y);
                ObjectAnimator.ofPropertyValuesHolder(mStarsIvs[i], pX, pY)
                        .setDuration(DUTATION*i).start();
            }
        } else {
            for(int i = 1 ; i < mStarsIds.length ; i++) {
                PropertyValuesHolder pX = PropertyValuesHolder.ofFloat("X", mStarsIvs[i].getX(),
                        0F);
                PropertyValuesHolder pY = PropertyValuesHolder.ofFloat("Y", mStarsIvs[i].getY(),
                        0F);
                ObjectAnimator.ofPropertyValuesHolder(mStarsIvs[i], pX, pY)
                        .setDuration(DUTATION*i).start();
            }
        }
        isStarsMenuOpen = !isStarsMenuOpen;
    }

    /**
     * 线型菜单
     * @param b
     */
    private void expandLinesMenu(boolean b){
        if(!b){
            for (int i = 1; i < mStarsIds.length; i++) {
                int x = (int) (mStarsIvs[mStarsIds.length-i].getWidth() + ITEM_INTERVAL) * (mStarsIds.length-i);
                PropertyValuesHolder pX = PropertyValuesHolder.ofFloat("X",mStarsIvs[mStarsIds.length-i].getX(),x);
                PropertyValuesHolder pRotation = PropertyValuesHolder.ofFloat("rotation",mStarsIvs[mStarsIds.length-i].getRotation(),360);
                ObjectAnimator.ofPropertyValuesHolder(mStarsIvs[mStarsIds.length-i],pX,pRotation).
                        setDuration(DUTATION*(mStarsIds.length-i)).start();
            }
        }else{
            for (int i = 1; i < 5; i++) {
                PropertyValuesHolder pRotation = PropertyValuesHolder.ofFloat("rotation",mStarsIvs[i].getRotation(),0);
                PropertyValuesHolder pX = PropertyValuesHolder.ofFloat("X",mStarsIvs[i].getX(),0F);
                ObjectAnimator.ofPropertyValuesHolder(mStarsIvs[i],pRotation,pX).setDuration(DUTATION*i).start();
            }
        }
        isStarsMenuOpen = !isStarsMenuOpen;
    }

    /**
     * 侧边栏展开菜单
     * @param b
     */
    private void expandFoldMenu(boolean b){

        if(!b){
            for (int i = 0; i < mFoldIds.length; i++) {
                int x = mFoldIvs[i].getWidth();
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("X",mFoldIvs[i].getX(),SCREEN_WIDTH - x);
                ObjectAnimator.ofPropertyValuesHolder(mFoldIvs[i],p1).setDuration(DUTATION*(i+1)).start();
            }
        }else{
            for (int i = 0; i < mFoldIds.length; i++) {
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("X",mFoldIvs[mFoldIds.length-i-1].getX(),SCREEN_WIDTH);
                ObjectAnimator.ofPropertyValuesHolder(mFoldIvs[mFoldIds.length-i-1],p2).setDuration(DUTATION*(mFoldIds.length-i)).start();
            }
        }
        isFoldMenuOpen = !isFoldMenuOpen;
    }
}
