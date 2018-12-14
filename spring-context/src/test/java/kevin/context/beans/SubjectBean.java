package kevin.context.beans;

import javax.inject.Named;

/*******************************************************************************
 * @author wj
 * @date 2018-12-12
 * @description 科目 bean 
 ******************************************************************************/
@Named
public class SubjectBean {

    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    @Override
    public String toString() {
        return "SubjectBean{" +
                "subjectName='" + subjectName + '\'' +
                '}';
    }
}
