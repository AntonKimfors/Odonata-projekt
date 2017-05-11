package com.example.murk.kwizgeeq.model;


/**
 * Created by Henrik on 04/04/2017.
 */

public class UserQuestion extends Question {
    private String questionText;
    private String imagePath;
    private double xPosition;
    private double yPosition;
    private String audioPath;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserQuestion && super.equals(obj)){
            UserQuestion o = (UserQuestion) obj;

            if(questionText !=null){
                if(o.getQuestionText()!=null){
                    if(!questionText.equals(o.getQuestionText()))
                        return false;
                } else {
                    return false;
                }
            }

            if(imagePath !=null){
                if(o.getImagePath()!=null){
                    if(!imagePath.equals(o.getImagePath()))
                        return false;
                } else {
                    return false;
                }
            }

            if(xPosition != o.xPosition){
                return false;
            }

            if(yPosition != yPosition){
                return false;
            }

            //all variables are equal
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Question: ").append(questionText)
                .append(System.lineSeparator()).append(super.toString());
        return sb.toString();
    }
}
