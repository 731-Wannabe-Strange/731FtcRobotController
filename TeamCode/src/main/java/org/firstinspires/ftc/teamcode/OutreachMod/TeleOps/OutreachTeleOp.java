package org.firstinspires.ftc.teamcode.OutreachMod.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.FlyWheelRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.MecanumDrive;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.Transfer;

@TeleOp(name = "Jojos TeleOp")


public class OutreachTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();

    Transfer transfer = new Transfer();

    FlyWheelRotation flywheel = new FlyWheelRotation();

    public boolean aButtonPressed;
    public boolean aButtonWasPressed;

    public boolean rightBumperAlreadyPressed;
    public boolean leftBumperAlreadyPressed;
    public boolean transferOn;


    @Override
    public void init() {
        drive.init(hardwareMap);
        transfer.init(hardwareMap);
        flywheel.init(hardwareMap);
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

        if (gamepad1.right_bumper && !rightBumperAlreadyPressed) {
            transferOn = !transferOn;
            if (transferOn) {
                transfer.transferMotor.setPower(1.0);
            } else {
                transfer.transferMotor.setPower(0.0);
            }
        }
        rightBumperAlreadyPressed = gamepad1.right_bumper;

        if (gamepad1.left_bumper && !leftBumperAlreadyPressed) {
            flywheel.toggleFlyWheelState();
        }
        leftBumperAlreadyPressed = gamepad1.left_bumper;

        drive.drive(forward, right, rightStickY, rightStickX);
    }

}
