
package ppts.com.quiz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizModel implements Parcelable
{

    @SerializedName("optionType")
    @Expose
    private String optionType;
    @SerializedName("optionValue")
    @Expose
    private String optionValue;
    @SerializedName("clicked")
    @Expose
    private Boolean clicked = false;
    @SerializedName("isRight")
    @Expose
    private Boolean isRight = false;
    public final static Creator<QuizModel> CREATOR = new Creator<QuizModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public QuizModel createFromParcel(Parcel in) {
            return new QuizModel(in);
        }

        public QuizModel[] newArray(int size) {
            return (new QuizModel[size]);
        }

    }
    ;

    protected QuizModel(Parcel in) {
        this.optionType = ((String) in.readValue((String.class.getClassLoader())));
        this.optionValue = ((String) in.readValue((String.class.getClassLoader())));
        this.clicked = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isRight = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public QuizModel() {
    }

    /**
     * @param optionValue
     * @param optionType
     */
    public QuizModel(String optionType, String optionValue) {
        super();
        this.optionType = optionType;
        this.optionValue = optionValue;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(optionType);
        dest.writeValue(optionValue);
        dest.writeValue(clicked);
        dest.writeValue(isRight);
    }

    public int describeContents() {
        return  0;
    }

}
