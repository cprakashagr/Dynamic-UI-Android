package com.cprakashagr.cardswipedemo.views;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cprakashagr.cardswipedemo.R;
import com.cprakashagr.cardswipedemo.animation.AnimatorListenerAdapter;
import com.cprakashagr.cardswipedemo.cardstack.CardStack;

import java.util.Random;

/**
 * AndroidSwipeableCardStack / wxj.swipeablecardstack.views / ${FILE_NAME}
 * Created by chandra on 4/13/16.
 */
public class MCQView extends View {

    public LayoutInflater inflater;
    public View view;

    public MCQView(final Context context, final CardStack cardStack) {
        super(context);
        inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.card_content_mcq, null);
        ((TextView)view.findViewById(R.id.title)).setText("MCQ Question");

        Button submitButton = (Button) view.findViewById(R.id.button2);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Your Option is Recorded." + new Random().nextInt(), Toast.LENGTH_SHORT).show();
                cardStack.discardTop(1);
            }
        });

        final RelativeLayout cardLayout = (RelativeLayout) view.findViewById(R.id.cardLayout);
        final CardView cardView = (CardView) view.findViewById(R.id.cardView);
        final ImageView imageView = (ImageView) view.findViewById(R.id.logo);
        cardLayout.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("MCQ Focus", "Focus: " + hasFocus);
//                cardView.animate().
                imageView.animate().setDuration(250).alpha(0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator arg0) {
                        super.onAnimationEnd(arg0);
                        cardView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
