# Go To Point Holonomic
This project uses 3 PD (Proportional, Derivative) Control Systems to manipulate a robot into moving to a target point on a coordinate plane while combining movements effectively. This is valid for holonomic robots, specifically those with mecanum-based wheels.

## How It Works
To use this project, you need 3 main sensors, one to track a robot's X position, one to track a Y Position, and one for the heading. The more accurate these measurements are, and the less it drifts, the better the results would be. Using V-SLAM, camera localization, or other forms of knowing your robot's global coordinate will help you reach your target effectively. As of now, the powers for the Mecanum Wheels are between -1 and 1, where -1 is full reverse and 1 is full forward. This is done since we treat movements like a unit-circle (allows to use magnitude and calculate angles easily).

PID is a control system that is unbiased to the aspect it is controlling. Since this is a simple project, this library only utilizes PD control, which is Proportional and Derivative. Proportional is simply `kP * error` where `kP` is a coefficient that allows you to control your position proportional to error. For example, if the units are inches, and if the robot is 1 inch away from the target position, it should not be traveling at full speed, but it should also have enough speed to reach the target position. For this, say we want to make sure our power then for the wheels are 0.4 when 1 inch away from the target, we can simply put 0.4 as the `kP` value.

`kD` is to dampen the velocity so that the robot does not overshoot a target position. It does this by multiplying a coefficient by the rate of change of error in a particular axis.

`error` is simply the `target position - current position` value. Until error is 0, you have not reached your target, but since this is not realistic, one must tune the threshold in the main `GoToPoint.java` file.  

Using three values: `xSpeed`, `ySpeed`, and `headingSpeed`, one can use those in conjunction to move a holonomic mecanum drivetrain.

## How Do I Use It
Look at the SampleUsage.java. Simply initialize the main class and pass in your global coordinates, and pass the targets and current positions as the parameters for a go to point function. You can also set (and tune) your PD coeffients by doing `goToPoint.setHeadingCoeffs(some kP double, some kD double);`. You can also reset your position using the `reset()` method.

Lastly, to drive to a coordinate, simply pass in the targetPose, and the currentPose by doing: `goToPoint.driveToCoordinate(currentPose, targetPose);`. A pose is essentially a position with 3 main axes: x, y, and heading.

## Demo

Coming soon


