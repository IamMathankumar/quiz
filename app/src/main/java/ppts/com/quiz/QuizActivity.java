package ppts.com.quiz;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;
import ppts.com.quiz.model.QuizModel;
import ppts.com.quiz.widget.RelativeLayoutHeight;

import static ppts.com.quiz.utils.ScreenUtils.getScreenHeight;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnClickListener, EasyVideoCallback {


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
    @BindView(R.id.qsnCount)
    TextView qsnCount;
    @BindView(R.id.typeOfQsn)
    TextView typeOfQsn;
    int rightAnswer = 3;
    QuizAdapter adapter;


    @BindView(R.id.imageQsn)
    ImageView imageQsn;
    @BindView(R.id.videoQsn)
    EasyVideoPlayer videoQsn;
    @BindView(R.id.relativeLayout)
    RelativeLayoutHeight relativeLayout;


    RecyclerView.LayoutManager layoutManagerGrid;
    RecyclerView.LayoutManager layoutManagerLinear;
    private static final String TEST_URL = "http://images.apple.com/media/us/home/2017/c2fcc71c-82e9-46a6-b3c5-beed27f3f0a0/tv-spot/sway/home-holiday-sway-cc-us-20171123_1280x720h.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
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
        imageQsn.setVisibility(View.GONE);
        //  layoutManagerLinear = new LinearLayoutManager(this);
        //  optionsList.setLayoutManager(layoutManagerLinear);
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        videoQsn.setCallback(this);

        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        videoQsn.setSource(Uri.parse(TEST_URL));

        // From here, the player view will show a progress indicator until the player is prepared.
        // Once it's prepared, the progress indicator goes away and the controls become enabled for the user to begin playback.
    }


    private List<QuizModel> a() {
        List<QuizModel> val = new ArrayList<>();
        QuizModel model = new QuizModel("A.) ", "Green");
        val.add(model);
        model = new QuizModel("B.) ", "Blue");
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
// Methods for the implemented EasyVideoCallback

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        // TODO handle
    }

    @Override
    public void onBuffering(int percent) {
        // TODO handle if needed
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        // TODO handle
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onPause() {
        super.onPause();
        // Make sure the player stops playing if the user presses the home button.
        videoQsn.pause();
    }

}