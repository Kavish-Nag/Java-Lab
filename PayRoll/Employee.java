abstract class Employee {
    String name;
    String panNo;
    String joiningDate;
    String designation;
    int empId;

    Employee(String name, String panNo, String joiningDate,
             String designation, int empId) {
        this.name = name;
        this.panNo = panNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    abstract double calcCTC();

    // For table
    abstract String getEmployeeType();
    abstract double getBaseSalary();
    abstract double getHealthIns();
    abstract double getBonus();
    abstract double getOptions();
}