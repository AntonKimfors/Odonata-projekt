package com.example.murk.kwizgeeq.model;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Murk on 2017-04-05.
 */

public class UserQuiz extends Quiz implements Serializable{
	
	public UserQuiz(String name, Color listColor) {
		super(name, listColor);
	}

	/*
	public void setQuestion(int index, Question question){
        getQuestions().set(index,question);
    }

    public void addQuestion(Question question) {
    	questions.add(question);
    }

    public void replaceQuestion(Question oldQuestion,Question newQuestion){
        int index = questions.indexOf(oldQuestion);
        setQuestion(index,newQuestion);
    }

    public void removeQuestion(Question question){
        getQuestions().remove(question);
    }*/

    public UserQuiz copyUserQuiz(){
        UserQuiz clonedQuiz = new UserQuiz(this.getName(),this.getListColor());
        for(Question question :getQuestions()){
            clonedQuiz.addQuestion(question);
        }
        return clonedQuiz;
    }


}
