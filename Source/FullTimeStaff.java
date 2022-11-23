public class FullTimeStaff extends Staff{

    protected int baseSalary;
    protected double bonusRate;

    public FullTimeStaff(String sID, String sName, int baseSalary, Double bonusRate) {
        super(sID, sName);
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public int getBaseSalary() {
        return this.baseSalary;
    }
    public double getBonusRate() {
        return this.bonusRate;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    @Override
    public double paySalary(int workedDays) {
        if (workedDays > 21) {
            return this.baseSalary * this.bonusRate + ((workedDays - 21)*100000);
        } else {
            return this.baseSalary * this.bonusRate;
        }
    }

    @Override
    public String toString() {
        return this.sID + "_" + this.sName + "_" + this.bonusRate + "_" + this.baseSalary;
    }
}
