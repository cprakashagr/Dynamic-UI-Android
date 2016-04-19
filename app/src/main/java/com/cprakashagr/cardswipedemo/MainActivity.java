package com.cprakashagr.cardswipedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cprakashagr.cardswipedemo.cardstack.CardStack;
import com.cprakashagr.cardswipedemo.model.CardModel;
import com.cprakashagr.cardswipedemo.model.JobModel;
import com.cprakashagr.cardswipedemo.model.MCQModel;

public class MainActivity extends AppCompatActivity {
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;
    private Button button;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mCardStack = (CardStack)findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);

        mCardAdapter = new CardsDataAdapter(getApplicationContext(), mCardStack);

/*
        Following add methods is similar to making a web service call. The return is an array which would contain data as:
        [
          {
            cardType: type01,
            cardData: {

            }
          },
          {
            cardType: type02,
            cardData: {

            }
          }
        ]

        For more card types, add its model and class and card specific behaviour.
*/

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

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.discardTop(1);
            }
        });

        reset = (Button) findViewById(R.id.reset);
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
