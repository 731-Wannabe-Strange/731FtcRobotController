package org.firstinspires.ftc.teamcode.OutreachMod.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.FlyWheelRotation;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.MecanumDrive;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.Intake;
import org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard.Transfer;

@TeleOp(name = "Jojos TeleOp")


public class OutreachTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();

    FlyWheelRotation flywheel = new FlyWheelRotation();
    Transfer transfer = new Transfer();

    public boolean leftBumper;
    public boolean leftBumperWasPressed;

    public boolean rightBumper;
    public boolean rightBumperAlreadyPressed;

    public boolean leftBumperAlreadyPressed;
    public boolean aAlreadyPressed;

    public boolean transferOn;
    public boolean intakeOn;


    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        flywheel.init(hardwareMap);
        transfer.init(hardwareMap);
        leftBumper = false;
        leftBumperWasPressed = false;
        rightBumperAlreadyPressed = false;
        rightBumper = false;
        aAlreadyPressed = false;

    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rightStickY = -gamepad1.right_stick_y;
        double rightStickX = gamepad1.right_stick_x;


        telemetry.addData("Front Left Motor Speed", drive.frontLeftMotor.getVelocity());
        telemetry.addData("Front Right Motor Speed", drive.frontRightMotor.getVelocity());
        telemetry.addData("Back Left Motor Speed", drive.backLeftMotor.getVelocity());
        telemetry.addData("Back Right Motor Speed", drive.backRightMotor.getVelocity());


        drive.drive(forward, right, rightStickX);

        if (!leftBumperWasPressed && gamepad1.left_bumper) {
            intakeOn = !intakeOn;
            if (intakeOn) {
                intake.intake.setPower(1.0);
            } else {
                intake.intake.setPower(0.0);
            }

        }
        leftBumperWasPressed = leftBumper;

        if (gamepad1.right_bumper && !rightBumperAlreadyPressed) {
            transferOn = !transferOn;
            if (transferOn) {
                transfer.transferMotor.setPower(1.0);
            } else {
                transfer.transferMotor.setPower(0.0);
            }
        }
        rightBumperAlreadyPressed = gamepad1.right_bumper;

        if (gamepad1.a && !aAlreadyPressed) {
            flywheel.toggleFlyWheelState();
        }
        aAlreadyPressed = gamepad1.a;

        flywheel.update(telemetry);

    }

}
