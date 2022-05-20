package com.example.myapplication;

public class TopicData {
    public String getTopicOne() {
        return topicOne;
    }

    public void setTopicOne(String topicOne) {
        this.topicOne = topicOne;
    }

    String topicOne;

    public String getTopicTwo() {
        return topicTwo;
    }

    public void setTopicTwo(String topicTwo) {
        this.topicTwo = topicTwo;
    }

    String topicTwo;

    public String getTopicThree() {
        return topicThree;
    }

    public void setTopicThree(String topicThree) {
        this.topicThree = topicThree;
    }

    String topicThree;

    public String getTopicFour() {
        return topicFour;
    }

    public void setTopicFour(String topicFour) {
        this.topicFour = topicFour;
    }

    String topicFour;
    public TopicData(String topicOne,String topicTwo,String topicThree,String topicFour) {
        this.topicOne = topicOne;
        this.topicTwo = topicTwo;
        this.topicThree = topicThree;
        this.topicFour = topicFour;
    }



}
