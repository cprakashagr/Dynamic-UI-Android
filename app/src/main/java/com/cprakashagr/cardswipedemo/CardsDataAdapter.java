package com.cprakashagr.cardswipedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cprakashagr.cardswipedemo.cardstack.CardStack;
import com.cprakashagr.cardswipedemo.model.CardModel;
import com.cprakashagr.cardswipedemo.views.JobView;
import com.cprakashagr.cardswipedemo.views.MCQView;

public class CardsDataAdapter extends ArrayAdapter<CardModel> {

    LayoutInflater inflater;
    CardStack cardStack;

    public CardsDataAdapter(Context context, CardStack cardStack) {
        super(context, R.layout.card_content);
        inflater = LayoutInflater.from(context);
        this.cardStack = cardStack;
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){

        CardModel object = getItem(position);
        switch (object.type) {

            case "MCQ":
                MCQView mcqView = new MCQView(getContext(), cardStack);
                return mcqView.view;

            case "JOB":
                JobView jobView = new JobView(getContext(), cardStack);
                return jobView.view;

            default:
                return contentView;
        }
    }

    @Override
    public void add(CardModel object) {
        super.add(object);
    }
}

