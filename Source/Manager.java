public class Manager extends FullTimeStaff{
    
    private int allowance;

    public Manager(String sID, String sName, int baseSalary, double bonusRate, int allowance) {
        super(sID, sName, baseSalary, bonusRate);
        this.allowance = allowance;
    }

    public int getAllowance() {
        return this.allowance;
    }

    @Override
    public double paySalary(int workedDays) {
        double salary = 0;
        if (workedDays > 21) {
            salary =  this.baseSalary * this.bonusRate + ((workedDays - 21)*100000);
        } else {
            salary = this.baseSalary * this.bonusRate;
        }   
        return salary + this.allowance; 
    }
    @Override
    public String toString() {
        return this.sID + "_" + this.sName + "_" + this.getBonusRate() + "_" + this.getBaseSalary() + "_" + this.allowance;
    }
}
