package ppts.com.quiz.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by mathankumar.k on 07-04-2017.
 */

public class UtilTypeface {

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public static Typeface Bold(Context c){
        Typeface font = Typeface.createFromAsset(c.getAssets(), "nasalization rg.ttf");
        return font;
    }
    public static Typeface Regular(Context c){
        Typeface font = Typeface.createFromAsset(c.getAssets(), "nasalization rg.ttf");
        return font;
    }

    public static Typeface Light(Context c){
        Typeface font = Typeface.createFromAsset(c.getAssets(), "Exo-Thin.otf");
        return font;
    }
}
