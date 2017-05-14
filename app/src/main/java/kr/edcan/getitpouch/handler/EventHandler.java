package kr.edcan.getitpouch.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

import kr.edcan.getitpouch.activity.RankingActivity;
import kr.edcan.getitpouch.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Junseok Oh on 2017-05-11.
 */

public class EventHandler {
    private Context context;

    public EventHandler(Context context) {
        this.context = context;
    }

    /**
     * Click Events
     */

    public void onPouchClicked(String productId) {
        // Work
        Log.e("[dududu]", "A");
    }

    public void onEventClicked(String eventId) {
        // Work
        Log.d("dududu", eventId);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventId));

        context.startActivity(intent);

    }

    public void onRankingClicked(String ranking) {
        // Work
        //Log.d(ranking);
        context.startActivity(new Intent(context, RankingActivity.class)
                .putExtra("ranking", ranking));
    }

}
