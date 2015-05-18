package com.kuanxue.kxw.bean;

import java.util.List;

/**
 * Created by lzh on 2015/5/11.
 */
public class CourseInfoBean {

    public String Status;
    public String Message;
    public List<CourseInfoData> Result;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<CourseInfoData> Result() {
        return Result;
    }

    public void setResult(List<CourseInfoData> Result) {
        this.Result = Result;
    }


    @Override
    public String toString() {
        return "CourseInfoBean{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", Result=" + Result +
                '}';
    }

    public static class CourseInfoData {
        public String studyCost;
        public String studyNum;
        public String lectureDate;
        public String status;
        public String description;
        public String name;
        public String teacherName;

        public String getStudyCost() {
            return studyCost;
        }

        public void setStudyCost(String studyCost) {
            this.studyCost = studyCost;
        }

        public String getStudyNum() {
            return studyNum;
        }

        public void setStudyNum(String studyNum) {
            this.studyNum = studyNum;
        }

        public String getLectureDate() {
            return lectureDate;
        }

        public void setLectureDate(String lectureDate) {
            this.lectureDate = lectureDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }


        @Override
        public String toString() {
            return "CourseInfoData{" +
                    "studyCost='" + studyCost + '\'' +
                    ", studyNum='" + studyNum + '\'' +
                    ", lectureDate='" + lectureDate + '\'' +
                    ", status='" + status + '\'' +
                    ", description='" + description + '\'' +
                    ", name='" + name + '\'' +
                    ", teacherName='" + teacherName + '\'' +
                    '}';
        }
    }
}
