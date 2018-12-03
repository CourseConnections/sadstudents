/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author soup
 */
public class Course {
    public String title;
    public String description;
    public String professor;
    public String prefix; //The acronym part of a course name; i.e. "ITCS"
    public String designation; //The number part of a course name; i.e. "1213" (stored in a String instead of int because of possible online classes, which have designation "UOL")
    public int CRN;
    
    public Course() { }
    
    public Course(String title, String description, String professor, String prefix, String designation, int CRN) {
        this.title = title;
        this.description = description;
        this.professor = professor;
        this.prefix = prefix;
        this.designation = designation;
        this.CRN = CRN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }
    
    public String getAbbr() {
        return prefix + "-" + designation;
    }
}
