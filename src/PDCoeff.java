public class PDCoeff {
    public double kP;
    public double kD;

    public PDCoeff(double p, double d) {
        kP = p;
        kD = d;
    }

    public PDCoeff(double p) {
        kP = p;
        kD = 0;
    }
}
