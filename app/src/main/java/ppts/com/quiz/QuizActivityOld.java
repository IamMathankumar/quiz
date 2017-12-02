package ppts.com.quiz;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

import static ppts.com.quiz.utils.ScreenUtils.getScreenHeight;

public class QuizActivityOld extends AppCompatActivity {

    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    @BindView(R.id.header)
    TextView header;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.circleView)
    CircleProgressView circleView;
    @BindView(R.id.textQsn)
    TextView textQsn;
    @BindView(R.id.optionTitleA)
    TextView optionTitleA;
    @BindView(R.id.optionACard)
    CardView optionACard;
    @BindView(R.id.optionTitleB)
    TextView optionTitleB;
    @BindView(R.id.optionBCard)
    CardView optionBCard;
    @BindView(R.id.optionTitleC)
    TextView optionTitleC;
    @BindView(R.id.optionCCard)
    CardView optionCCard;
    @BindView(R.id.optionTitleD)
    TextView optionTitleD;
    @BindView(R.id.optionDCard)
    CardView optionDCard;
    @BindView(R.id.qsnCount)
    TextView qsnCount;
    @BindView(R.id.typeOfQsn)
    TextView typeOfQsn;
    @BindView(R.id.optionAnswerA)
    TextView optionAnswerA;
    @BindView(R.id.optionAnswerB)
    TextView optionAnswerB;
    @BindView(R.id.optionAnswerC)
    TextView optionAnswerC;
    @BindView(R.id.optionAnswerD)
    TextView optionAnswerD;
    @BindView(R.id.optionWrongA)
    TextView optionWrongA;
    @BindView(R.id.optionWrongB)
    TextView optionWrongB;
    @BindView(R.id.optionWrongC)
    TextView optionWrongC;
    @BindView(R.id.optionWrongD)
    TextView optionWrongD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_old);
        ButterKnife.bind(this);
        textQsn.setMovementMethod(new ScrollingMovementMethod());
        textQsn.setScrollbarFadingEnabled(false);
        Bitmap anImage = ((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.background_quiz)).getBitmap();
        Blurry.with(this)
                .radius(10)
                .sampling(8)
                .from(anImage)
                .into(imgBackground);

        int height = getScreenHeight(this);
        textQsn.setMaxHeight(height / 3);
        //  countdown(60);
        reverseTimer(60);
    }

    @OnClick({R.id.optionACard, R.id.optionBCard, R.id.optionCCard, R.id.optionDCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.optionACard:
                redTextView(1);
                greenTextView(4);
                clicked(1);
                break;
            case R.id.optionBCard:

                redTextView(2);
                greenTextView(4);
                clicked(2);
                break;
            case R.id.optionCCard:

                redTextView(3);
                greenTextView(4);
                clicked(3);
                break;
            case R.id.optionDCard:

                greenTextView(4);
                clicked(4);
                break;
        }
    }

    private void redTextView1(Integer a) {
        switch (a) {
            case 1:
                optionWrongA.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                optionWrongA.setText(R.string.cross);
                break;
            case 2:
                optionWrongB.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                optionWrongB.setText(R.string.cross);
                break;
            case 3:
                optionWrongC.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                optionWrongC.setText(R.string.cross);
                break;
            case 4:
                optionWrongD.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                optionWrongD.setText(R.string.cross);
                break;

        }
    }

    private void greenTextView1(Integer a) {
        switch (a) {
            case 1:
                optionWrongA.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                optionWrongA.setText(R.string.tick);
                break;
            case 2:
                optionWrongB.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                optionWrongB.setText(R.string.tick);
                break;
            case 3:
                optionWrongC.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                optionWrongC.setText(R.string.tick);
                break;
            case 4:
                optionWrongD.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                optionWrongD.setText(R.string.tick);
                break;

        }
    }

    private void redTextView(Integer a) {
        switch (a) {
            case 1:
                optionAnswerA.setBackgroundResource(R.color.colorRed);
                optionAnswerA.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleA.setBackgroundResource(R.color.colorRed);
                optionWrongA.setText(R.string.cross);
                break;
            case 2:
                optionAnswerB.setBackgroundResource(R.color.colorRed);
                optionAnswerB.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleB.setBackgroundResource(R.color.colorRed);
                optionWrongB.setText(R.string.cross);
                break;
            case 3:
                optionAnswerC.setBackgroundResource(R.color.colorRed);
                optionAnswerC.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleC.setBackgroundResource(R.color.colorRed);
                optionWrongC.setText(R.string.cross);
                break;
            case 4:
                optionAnswerD.setBackgroundResource(R.color.colorRed);
                optionAnswerD.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleD.setBackgroundResource(R.color.colorRed);
                optionWrongD.setText(R.string.cross);
                break;

        }
    }

    private void greenTextView(Integer a) {
        switch (a) {
            case 1:
                optionAnswerA.setBackgroundResource(R.color.colorGreen);
                optionAnswerA.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleA.setBackgroundResource(R.color.colorGreen);
                optionWrongA.setText(R.string.tick);
                break;
            case 2:
                optionAnswerB.setBackgroundResource(R.color.colorGreen);
                optionAnswerB.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleB.setBackgroundResource(R.color.colorGreen);
                optionWrongB.setText(R.string.tick);
                break;
            case 3:
                optionAnswerC.setBackgroundResource(R.color.colorGreen);
                optionAnswerC.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleC.setBackgroundResource(R.color.colorGreen);
                optionWrongC.setText(R.string.tick);
                break;
            case 4:
                optionAnswerD.setBackgroundResource(R.color.colorGreen);
                optionAnswerD.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
                optionTitleD.setBackgroundResource(R.color.colorGreen);
                optionWrongD.setText(R.string.tick);
                break;

        }
    }

    private void clicked(Integer a) {
        switch (a) {
            case 1:
                optionBCard.setClickable(false);
                optionCCard.setClickable(false);
                optionDCard.setClickable(false);
                break;
            case 2:
                optionACard.setClickable(false);
                optionCCard.setClickable(false);
                optionDCard.setClickable(false);
                break;
            case 3:
                optionACard.setClickable(false);
                optionBCard.setClickable(false);
                optionDCard.setClickable(false);
                break;
            case 4:
                optionACard.setClickable(false);
                optionBCard.setClickable(false);
                optionCCard.setClickable(false);
                break;

        }
    }


    public void reverseTimer(final int Seconds) {
        circleView.setMaxValue(Seconds);
        circleView.setTextMode(TextMode.TEXT);
        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                System.out.println(seconds);
                circleView.setValue(Seconds - seconds);
                circleView.setText(seconds + "");
            }

            public void onFinish() {
                circleView.setValue(Seconds);
                circleView.setText(getString(R.string.thumb_up));
                System.out.println("Completed");
                cancel();

            }
        }.start();
    }


}
