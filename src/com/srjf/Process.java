package com.srjf;

public class Process {
    private int id;
    private int time;
    private int startTime;
    private int endTime;
    private int waitTime;
    private int arrivalTime;
    private int remainingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int waitTime) {
        this.arrivalTime = waitTime;
    }

    public int getRemainingTime(){
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime){
        this.remainingTime = remainingTime;
    }
}