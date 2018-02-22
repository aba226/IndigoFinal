package modele;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ModeleClient {


	//Declare Employees Table Columns
    private StringProperty client_id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty email;
    private StringProperty phone_number;
    private SimpleObjectProperty<Date> hire_date;
    private StringProperty job_id;
    private IntegerProperty salary;
    private DoubleProperty commission_pct;
    private IntegerProperty manager_id;
    private IntegerProperty department_id;
 
    //Constructor
    public  ModeleClient() {
        this.client_id = new SimpleStringProperty();
        this.first_name = new SimpleStringProperty();
        this.last_name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone_number = new SimpleStringProperty();
        this.hire_date = new SimpleObjectProperty<>();
        this.job_id = new SimpleStringProperty();
        this.salary = new SimpleIntegerProperty();
        this.commission_pct = new SimpleDoubleProperty();
        this.manager_id = new SimpleIntegerProperty();
        this.department_id = new SimpleIntegerProperty();
    }
    public ModeleClient(String firstName, String lastName) {
        this.first_name = new SimpleStringProperty(firstName);
        this.last_name = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
       // this.client_id = new SimpleStringProperty("some street");
        //this.postalCode = new SimpleIntegerProperty(1234);
        //this.city = new SimpleStringProperty("some city");
        //this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }
 
    //employee_id
    public String getEmployeeId() {
        return client_id.get();
    }
 
    public void setEmployeeId(String employeeId){
        this.client_id.set(employeeId);
    }
 
    public StringProperty employeeIdProperty(){
        return client_id;
    }
 
    //first_name
    public String getFirstName () {
        return first_name.get();
    }
 
    public void setFirstName(String firstName){
        this.first_name.set(firstName);
    }
 
    public StringProperty firstNameProperty() {
        return first_name;
    }
 
    //last_name
    public String getLastName () {
        return last_name.get();
    }
 
    public void setLastName(String lastName){
        this.last_name.set(lastName);
    }
 
    public StringProperty lastNameProperty() {
        return last_name;
    }
 
    //email
    public String getEmail () {
        return email.get();
    }
 
    public void setEmail (String email){
        this.email.set(email);
    }
 
    public StringProperty emailProperty() {
        return email;
    }
 
    //phone_number
    public String getPhoneNumber () {
        return phone_number.get();
    }
 
    public void setPhoneNumber (String phoneNumber){
        this.phone_number.set(phoneNumber);
    }
 
    public StringProperty phoneNumberProperty() {
        return phone_number;
    }
 
    //hire_date
    public Object getHireDate(){
        return hire_date.get();
    }
 
    public void setHireDate(Date hireDate){
        this.hire_date.set(hireDate);
    }
 
    public SimpleObjectProperty<Date> hireDateProperty(){
        return hire_date;
    }
 
    //job_id
    public String getJobId () {
        return job_id.get();
    }
 
    public void setJobId (String jobId){
        this.job_id.set(jobId);
    }
 
    public StringProperty jobIdProperty() {
        return job_id;
    }
 
    //salary
    public int getSalary() {
        return salary.get();
    }
 
    public void setSalary(int salary){
        this.salary.set(salary);
    }
 
    public IntegerProperty salaryProperty(){
        return salary;
    }
 
    //commission_pct
    public double getCommissionPct() {
        return commission_pct.get();
    }
 
    public void setCommissionPct(double commissionPct){
        this.commission_pct.set(commissionPct);
    }
 
    public DoubleProperty commissionPctProperty(){
        return commission_pct;
    }
 
    //manager_id
    public int getManagerId() {
        return manager_id.get();
    }
 
    public void setManagerId(int managerId){
        this.manager_id.set(managerId);
    }
 
    public IntegerProperty managerIdProperty(){
        return manager_id;
    }
 
    //department_id
    public int getDepartmanId() {
        return department_id.get();
    }
 
    public void setDepartmantId(int departmentId){
        this.manager_id.set(departmentId);
    }
 
    public IntegerProperty departmentIdProperty(){
        return department_id;
    }
//////////////////////////////////////////////////////////
    //DEUXIEME PARTIE
//////////////////////////////////////////////////////////
    //Select * from employees operation
    public static ObservableList<ModeleClient> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<ModeleClient> empList = FXCollections.observableArrayList();
 
        while (rs.next()) {
           ModeleClient emp = new ModeleClient();
           // emp.setEmployeeId(rs.getString(1));
            emp.setFirstName(rs.getString(2));
            emp.setLastName(rs.getString(3));
            emp.setEmail(rs.getString(4));
            emp.setPhoneNumber(rs.getString(5));
            /*emp.setHireDate(rs.getDate(5));
            emp.setJobId(rs.getString("JOB_ID"));
            emp.setSalary(rs.getInt("SALARY"));
            emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
            emp.setManagerId(rs.getInt("MANAGER_ID"));
            emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
            //Add employee to the ObservableList
             * 
             */            empList.add(emp);
            System.out.println("xa c'est quoi xa encore");
            System.out.println(emp);
            System.out.println("xa c'est quoi xa encore");
            System.out.println(empList );
        }
       
        //return empList (ObservableList of Employees)
        return empList;
    }
    
//////////////////////////////////////////////////////////
}

