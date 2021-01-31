public class Pose2d {
    public double x;
    public double y;
    public double heading;

    public Pose2d() {
        x = 0;
        y = 0;
        heading = 0;
    }

    public Pose2d(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }
}
