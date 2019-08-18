package org.linlinjava.litemall.wx.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class WxCourseInfo {
    private int coursePlanId;
    private String name;
    private int totalTime;
    private int peopleNum;
    private int peopleLeft;
    private LocalDate cDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public int getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(int coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getPeopleLeft() {
        return peopleLeft;
    }

    public void setPeopleLeft(int peopleLeft) {
        this.peopleLeft = peopleLeft;
    }

    public LocalDate getcDate() {
        return cDate;
    }

    public void setcDate(LocalDate cDate) {
        this.cDate = cDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
