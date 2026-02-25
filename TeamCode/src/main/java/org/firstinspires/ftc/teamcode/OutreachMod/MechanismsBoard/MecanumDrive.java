package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {
    public DcMotorEx frontLeftMotor;
    public DcMotorEx frontRightMotor;
    public DcMotorEx backLeftMotor;
    public DcMotorEx backRightMotor;

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "lff");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "rtf");
        backLeftMotor = hardwareMap.get(DcMotorEx.class, "lfb");
        backRightMotor = hardwareMap.get(DcMotorEx.class, "rtb");

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double forward, double strafe, double rotate) {
        frontLeftMotor.setPower(-forward*0.25 + rotate*0.25); // this motor was backwards
        backLeftMotor.setPower(forward*0.25 - rotate*0.25);
        frontRightMotor.setPower(-forward*0.25 - rotate*0.25);
        backRightMotor.setPower(-forward*0.25 - rotate*0.25);



    }
}

