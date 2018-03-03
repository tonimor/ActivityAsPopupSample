package tonimor.vdkans.activityaspopupsample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class PopupActivity extends Activity
{
    private View m_popupContainer = null;
    private boolean m_isClosing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        setFinishOnTouchOutside(false);

        m_popupContainer = findViewById(R.id.popwin_container);

        Button myButton = (Button)findViewById(R.id.my_button_again);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWin(RESULT_OK);
            }
        });

        showPopWin(250);
    }

    protected void finalizeActivity(int i_resultCode)
    {
        Intent i = new Intent();
        setResult(i_resultCode, i);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        if(!m_isClosing)
            dismissPopWin(RESULT_CANCELED);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// animation

    public void showPopWin(final int i_milisec) {

        m_popupContainer.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showPopWin();
            }
        }, i_milisec);
    }

    public void dismissPopWin(final int i_milisec, final int i_resultCode) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissPopWin(i_resultCode);
            }
        }, i_milisec);
    }

    private void showPopWin() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    showPopWinOnThread();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dismissPopWin(final int i_resultCode) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dismissPopWinOnThread(i_resultCode);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showPopWinOnThread()
    {
        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateDecelerateInterpolator());
        trans.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                m_popupContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });

        m_popupContainer.startAnimation(trans);
    }

    public void dismissPopWinOnThread(final int i_resultCode) {

        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateInterpolator());
        trans.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                m_isClosing = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                m_popupContainer.setVisibility(View.INVISIBLE);
                finalizeActivity(i_resultCode);
                m_isClosing = false;
            }
        });

        m_popupContainer.startAnimation(trans);
    }

}
