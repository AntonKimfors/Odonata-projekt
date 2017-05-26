package com.example.murk.kwizgeeq;

import com.example.murk.kwizgeeq.model.Statistics;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Are on 26/05/2017.
 */

public class TestStatistics {

    private static Statistics testStatistics;

    @BeforeClass
    public static void beforeClass(){
        testStatistics = new Statistics();
    }

    @Test
    public void testStatisticsCreation(){
        Statistics testStatisticsNew = new Statistics();

        assertEquals(testStatisticsNew.getQuizCount(), 0);
        assertEquals(testStatisticsNew.getQuestionCount(), 0);
        assertEquals(testStatisticsNew.getAnswerCorrectCount(), 0);
        assertEquals(testStatisticsNew.getAnswerIncorrectCount(), 0);
        assertEquals(testStatisticsNew.getSecondsSpent(), 0);
    }

    @Test
    public void testStatisticsCreationWithInput(){
        int quizCount = 1;
        int questionCount = 3;
        int answerCorrectCount = 2;
        int answerIncorrectCount = 1;
        int secondsSpent = 34;

        Statistics testStatisticsWithInput = new Statistics(quizCount, questionCount, answerCorrectCount, answerIncorrectCount, secondsSpent);

        assertEquals(testStatisticsWithInput.getQuizCount(), quizCount);
        assertEquals(testStatisticsWithInput.getQuestionCount(), questionCount);
        assertEquals(testStatisticsWithInput.getAnswerCorrectCount(), answerCorrectCount);
        assertEquals(testStatisticsWithInput.getAnswerIncorrectCount(), answerIncorrectCount);
        assertEquals(testStatisticsWithInput.getSecondsSpent(), secondsSpent);
    }

    @Test
    public void testStatisticsQuizIncremention(){
        int oldQuizCount = testStatistics.getQuizCount();

        testStatistics.incQuizCount();

        assertEquals(oldQuizCount, (testStatistics.getQuizCount() - 1));
    }

    @Test
    public void testStatisticsQuestionIncremention(){
        int oldQuestionCount = testStatistics.getQuestionCount();

        testStatistics.incQuestionCount();

        assertEquals(oldQuestionCount, (testStatistics.getQuestionCount() - 1));
    }

    @Test
    public void testStatisticsAnswerIncremention(){

    }

    @Test
    public void testStatisticsTimer(){

    }

    @Test
    public void testStatisticsMerging(){

    }

}
