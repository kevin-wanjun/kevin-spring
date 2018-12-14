package kevin.context.beans;

/*******************************************************************************
 * @author wj
 * @date 2018-12-12
 * @description 学生 bean 
 ******************************************************************************/
public class StudentBean {

    /**学生科目*/
    public String studentName;
    /**科目bean*/
    private SubjectBean subjectBean;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public SubjectBean getSubjectBean() {
        return subjectBean;
    }

    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "studentName='" + studentName + '\'' +
                ", subjectBean=" + subjectBean +
                '}';
    }
}
