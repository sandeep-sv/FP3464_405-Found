abstract class Employee implements SalaryCalculator {
    private String name;
    private int employeeId;

    public Employee(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

}

class Manager extends Employee {
    private double fixedSalary;
    private int daysWorked;

    public Manager(String name, int employeeId, double fixedSalary) {
        super(name, employeeId);
        this.fixedSalary = fixedSalary;
        this.daysWorked=0;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }

    public void setDaysWorked() {
        this.daysWorked += 1;
    }

    @Override
    public double calculateSalary() {
        // Assuming a 22-day work month
        return (getFixedSalary() / 22) * getDaysWorked();
    }
}