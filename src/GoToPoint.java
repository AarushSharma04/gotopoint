public class GoToPoint {
    private double lastTimeRead;
    private double lastXError;
    private double lastYError;
    private double lastHeadingError;

    public final double PI = Math.PI;
    public final double TAU = 2*PI;

    private PDCoeff translatePDController;
    private PDCoeff headingPDController;

    public GoToPoint() {
        translatePDController = new PDCoeff(0, 0);
        headingPDController = new PDCoeff(0, 0);
    }

    public void setHeadingCoeffs(double kP, double kD) {
        headingPDController = new PDCoeff(kP, kD);
    }

    public void setTranslateCoeffs(double kP, double kD) {
        headingPDController = new PDCoeff(kP, kD);
    }

    public void driveToCoordinate(Pose2d target, Pose2d current) {
        double currentTime = (double) System.currentTimeMillis() / 1000;

        double xError;
        double yError;
        double headingError;

        do {
            headingError = angleWrap(Math.toRadians(target.heading) - current.heading);
            xError = target.x - current.x;
            yError = target.y - current.y;

            double headingPD = (headingError * headingPDController.kP) + (((headingError - lastHeadingError) / (currentTime - lastTimeRead)) * headingPDController.kD);
            double xPD = (xError * translatePDController.kP) + (((xError - lastXError) / (currentTime - lastTimeRead)) * translatePDController.kD);
            double yPD = (yError * translatePDController.kP) + (((yError - lastXError) / (currentTime - lastTimeRead)) * translatePDController.kD);

            headingPD = clip(headingPD, -1, 1);
            xPD = clip(xPD, -1, 1);
            yPD = clip(yPD, -1, 1);

            double[] rotatedVector = normalizeVectorInput(xPD, yPD, current.heading);


        } while(Math.abs(xError) > 1 || Math.abs(yError) > 1 || Math.abs(headingError) > Math.toRadians(1));
    }

    public double angleWrap(double angle) {
        while (angle > Math.PI)
            angle -= TAU;
        while (angle < -Math.PI)
            angle += TAU;
        return angle;
    }

    public double clip(double var, double min, double max) {
        if(var < min) {
            return min;
        }

        if(var > max) {
            return max;
        }

        return var;
    }

    public double[] normalizeVectorInput(double x, double y, double rotateBy) {
        return new double[]{x*Math.cos(rotateBy) - y*Math.sin(rotateBy), x*Math.sin(rotateBy) - y*Math.cos(rotateBy)};
    }

    public double[] wheelSpeeds(double x, double y, double heading) {
        double[] wheelSpeeds = new double[4];

        wheelSpeeds[0] = x + y - heading;
        wheelSpeeds[1] = x - y - heading;
        wheelSpeeds[2] = x + y + heading;
        wheelSpeeds[3] = x - y + heading;

        return wheelSpeeds;
    }
}
