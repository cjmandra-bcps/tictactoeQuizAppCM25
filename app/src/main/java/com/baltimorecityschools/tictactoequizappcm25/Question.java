package com.baltimorecityschools.tictactoequizappcm25;

public class Question {
    private String qPrompt;
    private boolean correctAnswer;
    private int imageResourceId;


    public Question(String qPrompt, boolean correctAnswer, int imageResourceId) {
        this.qPrompt = qPrompt;
        this.correctAnswer = correctAnswer;
        this.imageResourceId = imageResourceId;

    }

    public Question() {
        qPrompt = "";
        correctAnswer = false;

    }

    public String getqPrompt() {
        return qPrompt;

    }

    public boolean isThisCorrectAnswer(boolean userAnswer) {
        System.out.println(" userAnswer == correctAnswer " + (userAnswer == correctAnswer));
        return userAnswer == correctAnswer;

    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    @Override
    public String toString() {
        return "Question{" + "qPrompt=" + qPrompt + ", correctAnswer=" + correctAnswer + '}';
    }
}
