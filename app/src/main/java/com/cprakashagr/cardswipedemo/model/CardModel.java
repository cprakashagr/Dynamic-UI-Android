package com.cprakashagr.cardswipedemo.model;

/**
 * AndroidSwipeableCardStack / wxj.swipeablecardstack.model / ${FILE_NAME}
 * Created by chandra on 4/13/16.
 */
public class CardModel {

    public JobModel jobModel;
    public MCQModel mcqModel;

    public String type;

    public CardModel() {

    }

    public CardModel(MCQModel model) {
        this.mcqModel = model;
        this.type = "MCQ";
    }

    public CardModel(JobModel model) {
        this.jobModel = model;
        this.type = "JOB";
    }
}
