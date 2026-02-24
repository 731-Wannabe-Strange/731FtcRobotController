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
        frontLeftMotor = hardwareMap.get(DcMotor.class, "lff");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rtf");
        backLeftMotor = hardwareMap.get(DcMotor.class, "lfb");
        backRightMotor = hardwareMap.get(DcMotor.class, "rtb");

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void driveFieldRelativeWithSnap(double forward, double right, double rightStickX, double rightStickY) {
        frontLeftMotor.setPower(-forward);
        backLeftMotor.setPower(forward);

        frontRightMotor.setPower(rightStickX);
        backRightMotor.setPower(rightStickX);

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

