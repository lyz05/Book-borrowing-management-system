/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch09;

/**
 *
 * @author jsby06
 */
public class Student {
    String stuId;
    String stuName;
    String sex;
    String birth;
    
    Student(){
        
    }
    Student(String stuId,String stuName,String sex,String birth){
        this.stuId=stuId;
        this.stuName=stuName;
        this.sex=sex;
        this.birth=birth;
    }
}
