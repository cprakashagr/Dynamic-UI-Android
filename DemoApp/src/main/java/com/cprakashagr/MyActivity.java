package com.cprakashagr;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cprakashagr.cardstack.CardStack;

import com.cprakashagr.model.CardModel;
import com.cprakashagr.model.JobModel;
import com.cprakashagr.model.MCQModel;


public class MyActivity extends Activity {
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;
    private Button button;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cprakashagr.R.layout.activity_my);

        mCardStack = (CardStack)findViewById(com.cprakashagr.R.id.container);

        mCardStack.setContentResource(com.cprakashagr.R.layout.card_content);
        mCardStack.setStackMargin(20);

        mCardAdapter = new CardsDataAdapter(getApplicationContext(), mCardStack);

        //  Over Loop for the entire objects from the WebService Return
        mCardAdapter.add(new CardModel(new JobModel()));
        mCardAdapter.add(new CardModel(new JobModel()));
        mCardAdapter.add(new CardModel(new JobModel()));
        mCardAdapter.add(new CardModel(new MCQModel()));
        mCardAdapter.add(new CardModel(new JobModel()));
        mCardAdapter.add(new CardModel(new JobModel()));
        mCardAdapter.add(new CardModel(new MCQModel()));
        mCardAdapter.add(new CardModel(new MCQModel()));

        mCardStack.setAdapter(mCardAdapter);
        updateCardStackBehaviour();

        if(mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }

        button = (Button) findViewById(com.cprakashagr.R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.discardTop(1);
            }
        });

        reset = (Button) findViewById(com.cprakashagr.R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.reset(true);
                updateCardStackBehaviour();
            }
        });

        mCardStack.setListener(new CardStack.CardEventListener() {
            @Override
            public boolean swipeEnd(int section, float distance) {
                if (distance > 300) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean swipeStart(int section, float distance) {
                return false;
            }

            @Override
            public boolean swipeContinue(int section, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void discarded(int mIndex, int direction) {
                updateCardStackBehaviour();
            }

            @Override
            public void topCardTapped() {
                Log.d("Swipe: ", "Tappped");
            }
        });
    }

    private void updateCardStackBehaviour() {

        //  Number of Cards in the Stack
        int currIndex = mCardStack.getCurrIndex();
        try {
            if (mCardAdapter.getItem(currIndex+1).type.equals("JOB") && mCardAdapter.getItem(currIndex).type.equals("JOB")) {
                mCardStack.setVisibleCardNum(3);
            } else {
                mCardStack.setVisibleCardNum(1);
            }
        } catch (IndexOutOfBoundsException e) {

        }

        //  Swipable feature
        try {
            if (mCardAdapter.getItem(currIndex).type.equals("JOB")) {
                mCardStack.setCanSwipe(true);
            } else if (mCardAdapter.getItem(currIndex).type.equals("MCQ")) {
                mCardStack.setCanSwipe(false);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
