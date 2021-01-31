public class SampleUsage {
    public static double xPosition;
    public static double yPosition;
    public static double heading;

    public void updateWheelSpeeds(double someX, double someY, double someHeading) {
        GoToPoint goToPoint = new GoToPoint(xPosition, yPosition, heading);

        while(Robot.isRunning) { //set robot condition
            goToPoint.driveToCoordinate(10,20, 90);
            goToPoint.driveToCoordinate(80,40, -150);

            goToPoint.reset();

            goToPoint.driveToCoordinate(0,0, 0);
        }

        // call update in state machine loop
    }
}
