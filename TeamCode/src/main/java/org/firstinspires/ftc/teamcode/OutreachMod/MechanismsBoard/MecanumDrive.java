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

    public void drive(double forward, double right, double rightStickX, double rightStickY) {
        frontLeftMotor.setPower(-forward);
        backLeftMotor.setPower(forward);

        frontRightMotor.setPower(rightStickX);
        backRightMotor.setPower(rightStickX);

    }
}

