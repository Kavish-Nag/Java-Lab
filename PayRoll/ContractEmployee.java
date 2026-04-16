class ContractEmployee extends Employee {

    int noOfHrs;
    double hourlyRate;

    ContractEmployee(String name, String panNo, String joiningDate,
                     String designation, int empId,
                     int noOfHrs, double hourlyRate) {

        super(name, panNo, joiningDate, designation, empId);
        this.noOfHrs = noOfHrs;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calcCTC() {
        return noOfHrs * hourlyRate;
    }

    @Override
    String getEmployeeType() {
        return "CT";
    }

    @Override
    double getBaseSalary() {
        return 0;
    }

    @Override
    double getHealthIns() {
        return 0;
    }

    @Override
    double getBonus() {
        return 0;
    }

    @Override
    double getOptions() {
        return 0;
    }
}