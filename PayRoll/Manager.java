class Manager extends FullTimeEmployee {

    double TA;
    double eduAllowance;

    Manager(String name, String panNo, String joiningDate,
            String designation, int empId,
            double baseSalary, double healthIns,
            double perfBonus, double options,
            double TA, double eduAllowance) {

        super(name, panNo, joiningDate, designation, empId,
              baseSalary, healthIns, perfBonus, options, "Manager");

        this.TA = TA;
        this.eduAllowance = eduAllowance;
    }

    @Override
    double calcCTC() {
        return baseSalary + perfBonus + TA + eduAllowance;
    }

    @Override
    String getEmployeeType() {
        return "MGR";
    }
}