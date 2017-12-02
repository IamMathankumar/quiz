package ppts.com.quiz;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;
import ppts.com.quiz.model.QuizModel;

import static ppts.com.quiz.utils.ScreenUtils.getScreenHeight;

public class QuizActivityText extends AppCompatActivity implements QuizAdapter.OnClickListener {


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
    @BindView(R.id.optionsList)
    RecyclerView optionsList;
    @BindView(R.id.dummy)
    View dummy;
    @BindView(R.id.subParent)
    ConstraintLayout subParent;
    @BindView(R.id.qsnCount)
    TextView qsnCount;
    @BindView(R.id.typeOfQsn)
    TextView typeOfQsn;
    int rightAnswer = 3;
    QuizAdapter adapter;
    RecyclerView.LayoutManager layoutManagerGrid;
    RecyclerView.LayoutManager layoutManagerLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_text);
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
        //   LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //    optionsList.setLayoutManager(layoutManager);
        layoutManagerGrid = new GridLayoutManager(this, 2);
        optionsList.setLayoutManager(layoutManagerGrid);
        adapter = new QuizAdapter();
        optionsList.setAdapter(adapter);
        adapter.addAll(a());
        adapter.setOnItemClickListener(this);
        layoutManagerLinear = new LinearLayoutManager(this);
        optionsList.setLayoutManager(layoutManagerLinear);

    }

    private List<QuizModel> a() {
        List<QuizModel> val = new ArrayList<>();
        QuizModel model = new QuizModel("A.) ", "Green is here");
        val.add(model);
        model = new QuizModel("B.) ", "Blue  is here");
        val.add(model);
        model = new QuizModel("C.) ", "Red  is here ");
        val.add(model);
        model = new QuizModel("D.) ", "White  is here  is here");
        val.add(model);
        return val;
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


    @Override
    public void onRootViewClick(View view, QuizModel viewModel, int position) {
        if (position == (rightAnswer - 1)) {
            adapter.updateRight(position);
        } else {
            adapter.updateWrong(position);
            adapter.updateRight(rightAnswer - 1);
        }
    }

    @Override
    public void onButtonClick(View view, QuizModel viewModel, int position) {

    }
}
