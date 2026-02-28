package org.firstinspires.ftc.teamcode.OutreachMod.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.ArmRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.FlyWheelRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.MecanumDrive;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.PushServo;

@TeleOp(name = "Jojos TeleOp")


public class OutreachTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();

    public boolean aButtonPressed;
    public boolean aButtonWasPressed;

    public boolean rightBumper;
    public boolean rightBumperWasPressed;


    @Override
    public void init() {
        drive.init(hardwareMap);
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

        rightBumper = gamepad1.right_bumper;
        rightBumperWasPressed = rightBumper;

        drive.drive(forward, right, rightStickY, rightStickX);
    }

}
