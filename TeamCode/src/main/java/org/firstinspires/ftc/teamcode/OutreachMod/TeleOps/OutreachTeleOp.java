package org.firstinspires.ftc.teamcode.OutreachMod.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.MecanumDrive;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.Intake;

@TeleOp(name = "Jojos TeleOp")


public class OutreachTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();

    public boolean leftBumper;
    public boolean leftBumperWasPressed;

    public boolean rightBumper;
    public boolean rightBumperWasPressed;


    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        leftBumper = false;
        leftBumperWasPressed = false;
        rightBumper = false;
        rightBumperWasPressed = false;
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double rightStickX = gamepad1.right_stick_x;

        leftBumperWasPressed = leftBumper;
        leftBumper = gamepad1.left_bumper;

        rightBumperWasPressed = rightBumper;
        rightBumper = gamepad1.right_bumper;

        telemetry.addData("Front Left Motor Speed", drive.frontLeftMotor.getVelocity());
        telemetry.addData("Front Right Motor Speed", drive.frontRightMotor.getVelocity());
        telemetry.addData("Back Left Motor Speed", drive.backLeftMotor.getVelocity());
        telemetry.addData("Back Right Motor Speed", drive.backRightMotor.getVelocity());


        drive.drive(forward, right, rightStickX);

        if (!leftBumperWasPressed && leftBumper) {
            intake.toggle();
        }

    }

}
