package sample;

public class StudentRecord {
    private String SID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private double finalMark;
    private String letterGrade;

    public StudentRecord (String SID, float assignments, float midterm, float finalExam){
        this.SID = SID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;
        this.finalMark = (this.assignments * 0.20) + (this.midterm * 0.30) + (this.finalExam * 0.50);

        // Calculate Letter Grade
        if (this.finalMark >= 80){
            this.letterGrade = "A";
        } else if (this.finalMark >= 70 && this.finalMark < 80){
            this.letterGrade = "B";
        } else if (this.finalMark >= 60 && this.finalMark < 70){
            this.letterGrade = "C";
        } else if (this.finalMark >= 50 && this.finalMark < 60){
            this.letterGrade = "D";
        } else if (this.finalMark < 50){
            this.letterGrade = "F";
        }

    }

    //    Getters and Setters
    public String getSID() {
        return this.SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public float getMidterm() {
        return this.midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getAssignments() {
        return this.assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getFinalExam() {
        return this.finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public double getFinalMark() {
        return this.finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public String getLetterGrade() {
        return this.letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

}
