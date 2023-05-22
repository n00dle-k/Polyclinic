package DB;

public class Reception {
    private Integer log_id;
    private String symptoms;
    private String treatment;
    private String patient_lname;
    private String patient_fname;
    private String patient_mname;
    private String employee_lname;
    private String employee_fname;
    private String employee_mname;
    private String diagnosis_id;
    private String recep_date;


    public Reception(String symptoms, String treatment, String patient_lname, String patient_fname, String patient_mname, String employee_lname, String employee_fname, String employee_mname, String diagnosis_id, String recep_date){
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.patient_lname = patient_lname;
        this.patient_fname = patient_fname;
        this.patient_mname = patient_mname;
        this.employee_lname = employee_lname;
        this.employee_fname = employee_fname;
        this.employee_mname = employee_mname;
        this.diagnosis_id = diagnosis_id;
        this.recep_date = recep_date;
    }

    public Reception(String patient_lname, String patient_fname, String patient_mname, String employee_lname, String employee_fname, String employee_mname, String recep_date){
        this.patient_lname = patient_lname;
        this.patient_fname = patient_fname;
        this.patient_mname = patient_mname;
        this.employee_lname = employee_lname;
        this.employee_fname = employee_fname;
        this.employee_mname = employee_mname;
        this.recep_date = recep_date;
    }

    public Reception(Integer log_id, String symptoms, String treatment, String patient_lname, String patient_fname, String patient_mname, String employee_lname, String employee_fname, String employee_mname, String diagnosis_id, String recep_date){
        this.log_id = log_id;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.patient_lname = patient_lname;
        this.patient_fname = patient_fname;
        this.patient_mname = patient_mname;
        this.employee_lname = employee_lname;
        this.employee_fname = employee_fname;
        this.employee_mname = employee_mname;
        this.diagnosis_id = diagnosis_id;
        this.recep_date = recep_date;
    }

    public Reception(Integer log_id, String patient_lname, String patient_fname, String patient_mname, String employee_lname, String employee_fname, String employee_mname, String recep_date){
        this.log_id = log_id;
        this.patient_lname = patient_lname;
        this.patient_fname = patient_fname;
        this.patient_mname = patient_mname;
        this.employee_lname = employee_lname;
        this.employee_fname = employee_fname;
        this.employee_mname = employee_mname;
        this.recep_date = recep_date;
    }

    public Reception(Integer log_id, String recep_date){
        this.log_id = log_id;
        this.recep_date = recep_date;
    }

    public Reception(String recep_date){
        this.recep_date = recep_date;
    }

    public Reception(String symptoms, String treatment, String diagnosis_id){
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.diagnosis_id = diagnosis_id;
    }

    public Reception(Integer log_id, String recep_date, String diagnosis_id, String treatment){
        this.log_id = log_id;
        this.recep_date = recep_date;
        this.diagnosis_id = diagnosis_id;
        this.treatment = treatment;
    }

    public Reception(){}

    public Integer getLog_id(){ return log_id;}
    public void setLog_id(Integer log_id){ this.log_id = log_id;}

    public String getSymptoms(){ return symptoms;}
    public void setSymptoms(String symptoms){this.symptoms = symptoms;}

    public String getTreatment(){ return treatment;}
    public void setTreatment(String treatment){this.treatment = treatment;}

    public String getPatient_lname(){ return patient_lname;}
    public void setPatient_lname(String patient_lname){this.patient_lname = patient_lname;}

    public String getPatient_fname(){ return patient_fname;}
    public void setPatient_fname(String patient_fname){this.patient_fname = patient_fname;}

    public String getPatient_mname(){ return patient_mname;}
    public void setPatient_mname(String patient_mname){this.patient_mname = patient_mname;}

    public String getEmployee_lname(){ return employee_lname;}
    public void setEmployee_lname(String employee_lname){ this.employee_lname = employee_lname;}

    public String getEmployee_fname(){ return employee_fname;}
    public void setEmployee_fname(String employee_fname){ this.employee_fname = employee_fname;}

    public String getEmployee_mname(){ return employee_mname;}
    public void setEmployee_mname(String employee_mname){ this.employee_mname = employee_mname;}

    public String getDiagnosis_id(){ return diagnosis_id;}
    public void setDiagnosis_id(String diagnosis_id){ this.diagnosis_id = diagnosis_id;}

    public String getRecep_date(){return recep_date;}
    public void setRecep_date(String recep_date){this.recep_date = recep_date;}
}
