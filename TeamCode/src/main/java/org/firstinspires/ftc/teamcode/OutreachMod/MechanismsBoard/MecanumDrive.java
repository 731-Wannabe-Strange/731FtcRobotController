package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    private double currentHeadingError = 0;
    private double currentRotatePower = 0;

    private IMU imu;

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }

    public void driveFieldRelativeWithSnap(double forward, double right, double rightStickX, double rightStickY) {
        double r = Math.hypot(rightStickY, rightStickX);
        double headingRad = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double newForward = forward * Math.cos(headingRad) + right * Math.sin(headingRad);
        double newRight = -forward * Math.sin(headingRad) + right * Math.cos(headingRad);
        if (r < 0.5) {
            // Stick not pressed hard enough: no snap
            currentHeadingError = 0;
            currentRotatePower = 0;
            this.drive(newForward, newRight, 0);
            return;
        }

        double theta = Math.atan2(rightStickY, rightStickX);
        double targetHeading = Math.toDegrees(theta);
        if (targetHeading < 0) targetHeading += 360;

        double currentHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        if (currentHeading < 0) {
            currentHeading += 360;
        }

        double error = targetHeading - currentHeading;
        if (error > 180) error -= 360;
        if (error < -180) error += 360;

        double kP = 0.01; // Tune this value
        double rotate = kP * error;
        rotate = Math.max(-1.0, Math.min(1.0, rotate)); // Clamp
        this.drive(newForward, newRight, rotate);
        currentHeadingError = error;
        currentRotatePower = rotate;

    }

    public void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        double largestSpeed = 1.0;
        largestSpeed = Math.max(largestSpeed, Math.abs(frontLeftPower));
        largestSpeed = Math.max(largestSpeed, Math.abs(frontRightPower));
        largestSpeed = Math.max(largestSpeed, Math.abs(backLeftPower));
        largestSpeed = Math.max(largestSpeed, Math.abs(backRightPower));
        frontLeftMotor.setPower(frontLeftPower / largestSpeed);
        frontRightMotor.setPower(frontRightPower / largestSpeed);
        backLeftMotor.setPower(backLeftPower / largestSpeed);
        backRightMotor.setPower(backRightPower / largestSpeed);
    }

    public void drive(double forward, double right, double rotate) {
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backLeftPower = forward - right + rotate;
        double backRightPower = forward + right - rotate;
        setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }

    public double getHeadingError() {
        return currentHeadingError;
    }

    public double getRotatePower() {
        return currentRotatePower;
    }

    public double getImuYawDegrees() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public void update() { // Empty for now
    }
}

