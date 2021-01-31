public class SampleUsage {
    public static double xPosition;
    public static double yPosition;
    public static double heading;

    public void updateWheelSpeeds(double someX, double someY, double someHeading) {
        GoToPoint goToPoint = new GoToPoint();
        goToPoint.setHeadingCoeffs(8, 0);
        goToPoint.setTranslateCoeffs(4, 0);

        while(Robot.isRunning) { //set robot condition
            goToPoint.driveToCoordinate(new Pose2d(20, 0, 180), new Pose2d(xPosition, yPosition, heading));
            goToPoint.driveToCoordinate(new Pose2d(40, 40, 90), new Pose2d(xPosition, yPosition, heading));
            goToPoint.driveToCoordinate(new Pose2d(0, 0, 0), new Pose2d(xPosition, yPosition, heading));
        }
    }
}
