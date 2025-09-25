package org.firstinspires.ftc.teamcode.OutreachMod.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.ArmRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.FlyWheelRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.MecanumDrive;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.PushServo;

@TeleOp(name = "Outreach TeleOp")


public class OutreachTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    ArmRotation arm = new ArmRotation();

    FlyWheelRotation wheel = new FlyWheelRotation();

    PushServo pushServo = new PushServo();

    public boolean aButtonPressed;
    public boolean aButtonWasPressed;

    public boolean rightBumper = gamepad1.right_bumper;
    public boolean rightBumperWasPressed;


    @Override
    public void init() {
        drive.init(hardwareMap);
        arm.init(hardwareMap);
        wheel.init(hardwareMap);
        pushServo.init(hardwareMap);
        aButtonPressed = false;
        aButtonWasPressed = false;
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double rightStickX = gamepad1.right_stick_x;

        aButtonWasPressed = aButtonPressed;
        aButtonPressed = gamepad1.a;
        arm.setArmState(gamepad1.dpad_up, gamepad1.dpad_down);

        if (aButtonPressed && !aButtonWasPressed) {
            arm.currentArmState = ArmRotation.ArmStates.MANUAL_STOP;
        }


        if (rightBumper && !rightBumperWasPressed) {
            wheel.toggleFlyWheelState();
        }

        rightBumperWasPressed = rightBumper;

        drive.driveFieldRelativeWithSnap(forward, right, rightStickX, rightStickY);
        telemetry.addData("Target Heading Error", drive.getHeadingError());
        telemetry.addData("Calculated Rotate Power", drive.getRotatePower());
        telemetry.addData("Current Robot Heading (IMU)", drive.getImuYawDegrees());

        telemetry.addData("Current Arm State", arm.getLastArmState());
        telemetry.addData("Motor 1 Position", arm.getMotor1Position());
        telemetry.addData("Motor 2 Position", arm.getMotor2Position());
        telemetry.update();
        arm.update(); // Empty for now, just good practice
        drive.update(); // Empty for now, just good practice
        wheel.update(); // Empty for now, just good practice
    }

    /* public FlyWheelRotation.FlyWheelStates toggle() {
    } */

}
