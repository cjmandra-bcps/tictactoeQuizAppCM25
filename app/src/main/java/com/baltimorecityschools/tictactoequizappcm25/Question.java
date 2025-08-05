package com.baltimorecityschools.tictactoequizappcm25;

public class Question {
    private String qPrompt;
    private boolean correctAnswer;


    public Question(String qPrompt, boolean correctAnswer) {
        this.qPrompt = qPrompt;
        this.correctAnswer = correctAnswer;

    }

    public Question() {
        qPrompt = "";
        correctAnswer = false;

    }

    public String getqPrompt() {
        return qPrompt;

    }

    public boolean isThisCorrectAnswer(boolean userAnswer) {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" + "qPrompt=" + qPrompt + ", correctAnswer=" + correctAnswer + '}';
    }
}
