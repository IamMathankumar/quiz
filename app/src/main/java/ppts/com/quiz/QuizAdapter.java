package ppts.com.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ppts.com.quiz.model.QuizModel;


/**
 * Created by mathankumar.k on 08-09-2017. fg
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> implements View.OnClickListener {


    private List<QuizModel> qualityList = new ArrayList<>();
    private Context c;
    boolean clicked = false;
    private OnClickListener onItemClickListener;

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnClickListener {

        void onRootViewClick(View view, QuizModel viewModel, int position);

        void onButtonClick(View view, QuizModel viewModel, int position);

    }

    QuizAdapter() {
    }


    private void redTextView(ViewHolder h) {
        h.optionValue.setBackgroundResource(R.color.colorRed);
        h.optionValue.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
        h.optionTitle.setBackgroundResource(R.color.colorRed);
        h.optionAnswer.setText(R.string.cross);
    }

    private void greenTextView(ViewHolder h) {
        h.optionValue.setBackgroundResource(R.color.colorGreen);
        h.optionValue.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
        h.optionTitle.setBackgroundResource(R.color.colorGreen);
        h.optionAnswer.setText(R.string.tick);

    }

    void updateRight(int position) {
        this.qualityList.get(position)
                .setClicked(true);
        this.qualityList.get(position)
                .setIsRight(true);
        notifyItemChanged(position);

    }

    void updateWrong(int position) {
        this.qualityList.get(position)
                .setClicked(true);
        this.qualityList.get(position)
                .setIsRight(false);
        notifyItemChanged(position);

    }

    void addOne(QuizModel checkList) {
        this.qualityList.add(checkList);
        notifyDataSetChanged();
    }

    public void addAll(List<QuizModel> checkList) {
        if (this.qualityList.size() > 0) {
            this.qualityList.clear();
        }
        clicked = false;
        this.qualityList.addAll(checkList);
        notifyDataSetChanged();
    }

    void clear() {
        if (this.qualityList.size() > 0) {
            this.qualityList.clear();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_quiz, parent, false);
        view.setOnClickListener(this);
        c = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        if (!clicked) {
            clicked = true;
            QuizModel model = (QuizModel) view.getTag();
            int position = qualityList.indexOf(model);
            qualityList.get(position).setClicked(true);
            if (view.getId() == R.id.optionCard) {
                onItemClickListener.onRootViewClick(view, (QuizModel) view.getTag(), position);
            } else {
                onItemClickListener.onButtonClick(view, (QuizModel) view.getTag(), position);
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.optionTitle)
        TextView optionTitle;
        @BindView(R.id.optionValue)
        TextView optionValue;
        @BindView(R.id.optionAnswer)
        TextView optionAnswer;
        @BindView(R.id.optionCard)
        CardView optionCard;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder h, int position) {

        QuizModel item = qualityList.get(position);
        h.optionTitle.setText(item.getOptionType());
        h.optionValue.setText(item.getOptionValue());
        if (item.getClicked()) {
            if (item.getIsRight()) {
                greenTextView(h);
            } else {
                redTextView(h);
            }
        }
        h.optionValue.setMinLines(2);
        h.itemView.setTag(item);
      /*  QuizModel dueDate = "Due date <font color=#ffcc00>2017-04-12</font>";
        holder.tDueDate.setText(fromHtml(dueDate));
        QuizModel updatedOn = "Updated on<font color=#ffcc00>2017-04-12</font>";
        holder.tUpdatedOn.setText(fromHtml(updatedOn));*/
    }

    @Override
    public int getItemCount() {
        return qualityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
