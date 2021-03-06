package com.kwizgeeq.activity;

import android.app.ListActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.kwizgeeq.R;
import com.kwizgeeq.controller.QuizListController;
import com.kwizgeeq.databinding.ActivityQuizListBinding;
import com.kwizgeeq.view.QuizListView;

/**
 * Created by akimfors on 2017-05-05.
 *
 * @author Anton Kimfors
 * revised by Henrik Håkansson, Are Ehnberg and Marcus Olsson Lindvärn
 */

public class QuizListActivity extends ListActivity {

    private QuizListController controller;
    private QuizListView view;

    private int editQuizRequestCode = 1;
    private int createQuizRequestCode = 2;
    private int questioneerRequestCode = 3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);

        ActivityQuizListBinding binding;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_quiz_list);

        view = new QuizListView(getListView(), this, this, EditQuizActivity.class,
                QuestioneerActivity.class, GlobalStatisticsActivity.class, (FloatingActionButton) findViewById(R.id.fab),
                editQuizRequestCode, createQuizRequestCode, questioneerRequestCode);
        controller = new QuizListController(view, this);

        binding.setController(controller);
        view.addObserver(controller);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == createQuizRequestCode && resultCode == RESULT_OK){
            controller.addQuiz(data.getSerializableExtra("quiz"));
        }

        if(requestCode == editQuizRequestCode && resultCode == RESULT_OK){
            controller.replaceQuiz(data.getSerializableExtra("quiz"), data.getIntExtra("quizIndex",0));
        }

        if(requestCode == questioneerRequestCode && resultCode == RESULT_OK) {
            controller.replaceQuiz(data.getSerializableExtra("quiz"), data.getIntExtra("quizIndex",0));
            controller.updateStatistics(data.getSerializableExtra("quiz"));
        }
    }
    


}
