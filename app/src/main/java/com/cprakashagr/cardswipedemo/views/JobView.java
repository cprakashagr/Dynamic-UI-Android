package com.cprakashagr.cardswipedemo.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cprakashagr.cardswipedemo.R;
import com.cprakashagr.cardswipedemo.cardstack.CardStack;

/**
 * AndroidSwipeableCardStack / wxj.swipeablecardstack.views / ${FILE_NAME}
 * Created by chandra on 4/14/16.
 */
public class JobView {

    public LayoutInflater inflater;
    public View view;

    public JobView(Context context, CardStack cardStack) {
        inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.card_content_job, null);
        ((TextView)view.findViewById(R.id.content)).setText("Job Card");
        ((TextView)view.findViewById(R.id.content2)).setText("Swipable");
    }
}
