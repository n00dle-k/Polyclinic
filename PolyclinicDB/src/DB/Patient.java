package DB;

public class Patient {
    private Integer patient_id;
    private String first_name;
    private String last_name;
    private String midle_name;
    private String birth_date;
    private String phone;
    private String address;
    private String num_passport;
    private String snils;
    private String med_policy;

    public Patient (String first_name, String last_name, String midle_name, String birth_date, String phone, String address, String num_passport, String snils, String med_policy){
        this.first_name = first_name;
        this.last_name = last_name;
        this.midle_name = midle_name;
        this.birth_date = birth_date;
        this.phone = phone;
        this.address = address;
        this.num_passport = num_passport;
        this.snils = snils;
        this.med_policy = med_policy;
    }

    public Patient(Integer patient_id, String first_name, String last_name, String midle_name, String birth_date, String phone, String address, String num_passport, String snils, String med_policy){
        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.midle_name = midle_name;
        this.birth_date = birth_date;
        this.phone = phone;
        this.address = address;
        this.num_passport = num_passport;
        this.snils = snils;
        this.med_policy = med_policy;
    }

    public Patient(String last_name, String first_name, String midle_name){
        this.last_name = last_name;
        this.first_name = first_name;
        this.midle_name = midle_name;
    }

    public Patient(Integer patient_id, String last_name, String first_name, String midle_name){
        this.patient_id = patient_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.midle_name = midle_name;
    }

    public Patient() {};

    public Integer getPatient_id() {return patient_id;}
    public void setPatient_id(Integer patient_id){this.patient_id = patient_id;}

    public String getFirst_name(){
        return first_name;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getLast_name(){
        return last_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getMidle_name(){
        return midle_name;
    }
    public void setMidle_name(String midle_name){
        this.midle_name = midle_name;
    }

    public String getBirth_date(){
        return birth_date;
    }
    public void setBirth_date(String birth_date){
        this.birth_date = birth_date;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getNum_passport(){
        return num_passport;
    }
    public void setNum_passport(String num_passport){
        this.num_passport = num_passport;
    }

    public String getSnils(){
        return snils;
    }
    public void setSnils(String snils){
        this.snils = snils;
    }

    public String getMed_policy(){
        return med_policy;
    }
    public void setMed_policy(String med_policy){
        this.med_policy = med_policy;
    }
}
