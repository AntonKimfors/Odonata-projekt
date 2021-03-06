package com.kwizgeeq.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kwizgeeq.R;
import com.kwizgeeq.model.Answer;
import com.kwizgeeq.model.AnswerType;
import com.kwizgeeq.model.Question;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created by Are on 04/05/2017.
 *
 * * @author Are Ehnberg
 * revised by Marcus Olsson Lindvärn, Anton Kimfors, Henrik Håkansson
 */

public class QuestioneerView extends Observable{

    private Window window;
    private TextView quizLabel;
    private TextView questionLabel;
    private TextView progressNumbers;
    private ImageView questionImage;
    private ProgressBar progressBar;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;

    private Activity activity;

    public QuestioneerView(Activity activity) {
        this.activity = activity;
        this.window = activity.getWindow();
        this.quizLabel = (TextView) activity.findViewById(R.id.quizLabel);
        this.questionLabel = (TextView) activity.findViewById(R.id.questLabel);
        this.progressNumbers = (TextView) activity.findViewById(R.id.progressNumbers);
        this.questionImage = (ImageView) activity.findViewById(R.id.imageView);
        this.progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        this.answerButton1 = (Button) activity.findViewById(R.id.answerButton1);
        this.answerButton2 = (Button) activity.findViewById(R.id.answerButton2);
        this.answerButton3 = (Button) activity.findViewById(R.id.answerButton3);
        this.answerButton4 = (Button) activity.findViewById(R.id.answerButton4);
    }

    public void flashCorrectAnswer(View view) {
        flashAnswer(view, activity.getResources().getColor(R.color.colorCorrectAnswer, null));
    }

    public void flashIncorrectAnswer(View view) {
        flashAnswer(view, activity.getResources().getColor(R.color.colorIncorrectAnswer, null));
    }

    private void flashAnswer(View view, int color) {
        Drawable f1 = new ColorDrawable(color);
        Drawable f2 = view.getBackground();
        AnimationDrawable a = new AnimationDrawable();
        a.addFrame(f2, 250);
        a.addFrame(f1, 250);
        a.addFrame(f2, 250);
        a.addFrame(f1, 1000);
        a.addFrame(f2, 0);
        a.setOneShot(true);
        view.setBackground(a);
        a.start();
        waitForFlashAnimation();
    }

    private void waitForFlashAnimation(){
        setWindowUntouchable(true);
        new CountDownTimer(2250, 2250){
            public void onTick(long l){

            }
            public void onFinish(){
                setWindowUntouchable(false);
                setChanged();
                notifyObservers("question done");
            }
        }.start();
    }

    private void setWindowUntouchable(boolean untouchable){
        if(untouchable){
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void updateQuizRelatedItems(String quizName, int quizSize, int quizColor){
        quizLabel.setText(quizName);
        progressBar.setMax(quizSize);
        progressBar.getProgressDrawable().setColorFilter(quizColor, PorterDuff.Mode.SRC_IN);
    }

    public void updateQuestioneer(Question question, int currentQuestion, int quizSize){
        resetQuestioneerForUpdate();

        Iterator answerIterator = question.answerIterator(true);

        progressNumbers.setText(currentQuestion + " / " + quizSize);
        progressBar.setProgress(currentQuestion);
        questionLabel.setText(question.getQuestionText());
        if (question.getImagePath() != null){
            try {
                InputStream is = activity.getContentResolver().openInputStream(Uri.parse(question.getImagePath()));
                questionImage.setImageDrawable(Drawable.createFromStream(is, question.getImagePath()));
                questionImage.setImageAlpha(255);
            } catch (FileNotFoundException e) {
                questionImage.setImageAlpha(0);
            }
        }
        updateAnswerButtons(answerIterator);
    }

    private void updateAnswerButtons(Iterator answerIterator){
        for(int i = 1; i <= 4; i++){
            Answer answer = (Answer)answerIterator.next();
            switch (i) {
                case 1: answerButton1.setTag(answer);
                case 2: answerButton2.setTag(answer);
                case 3: answerButton3.setTag(answer);
                case 4: answerButton4.setTag(answer);
            }

            if(answer.getAnswerType() == AnswerType.TEXT) {
                switch (i) {
                    case 1: answerButton1.setText(answer.getData());
                    case 2: answerButton2.setText(answer.getData());
                    case 3: answerButton3.setText(answer.getData());
                    case 4: answerButton4.setText(answer.getData());
                }
            } else if (answer.getAnswerType() == AnswerType.IMAGE) {
                try {
                    InputStream is = activity.getContentResolver().openInputStream(Uri.parse(answer.getData()));
                    switch (i) {
                        case 1: answerButton1.setBackground(Drawable.createFromStream(is, answer.getData()));
                        case 2: answerButton2.setBackground(Drawable.createFromStream(is, answer.getData()));
                        case 3: answerButton3.setBackground(Drawable.createFromStream(is, answer.getData()));
                        case 4: answerButton4.setBackground(Drawable.createFromStream(is, answer.getData()));
                    }
                } catch (FileNotFoundException e) {
                    switch (i) {
                        case 1: answerButton1.setText("IMAGE NOT FOUND");
                        case 2: answerButton2.setText("IMAGE NOT FOUND");
                        case 3: answerButton3.setText("IMAGE NOT FOUND");
                        case 4: answerButton4.setText("IMAGE NOT FOUND");
                    }
                }
            }
        }
    }

    private void resetQuestioneerForUpdate(){
        answerButton1.setText("");
        answerButton2.setText("");
        answerButton3.setText("");
        answerButton4.setText("");
        answerButton1.setBackgroundColor(activity.getResources().getColor(R.color.colorQuestioneerButton, null));
        answerButton2.setBackgroundColor(activity.getResources().getColor(R.color.colorQuestioneerButton, null));
        answerButton3.setBackgroundColor(activity.getResources().getColor(R.color.colorQuestioneerButton, null));
        answerButton4.setBackgroundColor(activity.getResources().getColor(R.color.colorQuestioneerButton, null));
        questionImage.setImageAlpha(0);
    }

    public void closeQuestioneer(){
        activity.finish();
    }

    public void showCloseQuizDialog(String quizName){
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing quiz: " + quizName)
                .setMessage("Are you sure you want to exit this quiz?\n(Your progress will not be saved.)")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeQuestioneer();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
