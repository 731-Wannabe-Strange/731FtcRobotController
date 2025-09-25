package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * ProgrammingBoardV9 serves as a hardware abstraction layer for FTC programming board components.
 * <p>
 * It provides initialization and utility methods for motors, servos, sensors (color, distance, touch, potentiometer),
 * and an IMU for retrieving orientation data.
 */
public class ProgrammingBoard {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private AnalogInput pot;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    private IMU imu;

    /**
     * Initializes all hardware components using the provided HardwareMap.
     *
     * @param hardwareMap FTC hardware mapping object
     */
    public void init(HardwareMap hardwareMap) {
        // implementation...
    }

    /**
     * Retrieves the yaw heading of the robot using the IMU.
     *
     * @param angleUnit the unit of measurement (degrees or radians)
     * @return yaw angle in specified angle unit
     */
    public double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    /**
     * Gets the red color value detected by the color sensor.
     *
     * @return red intensity value
     */
    public int getAmountRed() {
        return colorSensor.red();
    }

    /**
     * Gets the blue color value detected by the color sensor.
     *
     * @return blue intensity value
     */
    public int getAmountBlue() {
        return colorSensor.blue();
    }

    /**
     * Gets the green color value detected by the color sensor.
     *
     * @return green intensity value
     */
    public int getAmountGreen() {
        return colorSensor.green();
    }

    /**
     * Returns the distance measured by the distance sensor.
     *
     * @param du unit of distance (e.g. INCH, CM)
     * @return distance reading in specified unit
     */
    public double getDistance(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }

    /**
     * Sets the motor speed.
     *
     * @param speed value from -1.0 to 1.0
     */
    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
    }

    /**
     * Sets the motor's zero power behavior.
     *
     * @param zeroPowerBehavior behavior when power is set to zero (e.g. BRAKE or FLOAT)
     */
    public void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    /**
     * Checks whether the touch sensor is pressed.
     *
     * @return true if pressed, false otherwise
     */
    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }

    /**
     * Calculates the number of motor shaft rotations.
     *
     * @return rotations based on encoder position and ticks per revolution
     */
    public double getMotorRotations() {
        return motor.getCurrentPosition() / ticksPerRotation;
    }

    /**
     * Gets the current position of the servo.
     *
     * @return position from 0.0 to 1.0
     */
    public double getServoPosition() {
        return servo.getPosition();
    }

    /**
     * Sets the position of the servo.
     *
     * @param position value from 0.0 to 1.0
     */
    public void setServoPosition(double position) {
        servo.setPosition(position);
    }

    /**
     * Squares an input value while preserving its sign.
     *
     * @param input the value to be squared
     * @return squared value with original sign
     */
    public double squareInputWithSign(double input) {
        double output = input * input;
        if (input < 0) {
            output *= -1;
        }
        return output;
    }

    /**
     * Converts the potentiometer voltage to a corresponding angle (0° to 270°).
     *
     * @return angle reading from potentiometer
     */
    public double getPotAngle() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
    }

    /**
     * Normalizes the potentiometer voltage to a value between 0 and 1.
     *
     * @return normalized potentiometer value
     */
    public double getPotNormalized() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 1);
    }

    /**
     * Returns a list of test items for verifying connected hardware components.
     *
     * @return ArrayList of TestItem objects
     */
}
