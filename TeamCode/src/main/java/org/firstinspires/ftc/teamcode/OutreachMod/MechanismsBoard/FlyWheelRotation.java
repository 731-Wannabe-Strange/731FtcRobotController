package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlyWheelRotation {
    public DcMotorEx flyWheelMotor1;
    public DcMotorEx flyWheelMotor2;


    public static final int RESTING_MOTION = 0;
    public static final double SPINNING_MOTION = 0.5;

    private FlyWheelStates currentFlyWheelState = FlyWheelStates.RESTING;

    public enum FlyWheelStates {
        RESTING,
        SPINNING
    }

    public void init(HardwareMap hardwareMap) {
        flyWheelMotor1 = (DcMotorEx) hardwareMap.dcMotor.get("flywheelr");
        flyWheelMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flyWheelMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyWheelMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flyWheelMotor1.setDirection(DcMotor.Direction.FORWARD);
        flyWheelMotor1.setPower(RESTING_MOTION);
        flyWheelMotor1.setVelocityPIDFCoefficients(1, 0, 0, 0);
        flyWheelMotor2.setVelocityPIDFCoefficients(1, 0, 0, 0);
        flyWheelMotor2 = (DcMotorEx) hardwareMap.dcMotor.get("flywheell");
        flyWheelMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flyWheelMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyWheelMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flyWheelMotor2.setDirection(DcMotor.Direction.FORWARD);
        flyWheelMotor2.setPower(RESTING_MOTION);
    }

private void evaluateFlyWheelState() {
        switch (currentFlyWheelState) {
            case RESTING:
                flyWheelMotor1.setPower(RESTING_MOTION);
                flyWheelMotor2.setPower(RESTING_MOTION);
                break;
            case SPINNING:
                flyWheelMotor1.setPower(SPINNING_MOTION);
                flyWheelMotor2.setPower(SPINNING_MOTION);
                break;
        }
}

    public void toggleFlyWheelState() {
        switch (currentFlyWheelState) {
            case RESTING:
                currentFlyWheelState = FlyWheelStates.SPINNING;
                break;
            case SPINNING:
                currentFlyWheelState = FlyWheelStates.RESTING;
                break;
            default:
                flyWheelMotor1.setPower(RESTING_MOTION);
                break;
        }
        evaluateFlyWheelState();
    }

    public void update(Telemetry telemetry) {
        telemetry.addData("FlyWheel State", currentFlyWheelState.toString());
        telemetry.addData("FlyWheel Motor 1 Power", flyWheelMotor1.getPower());
        telemetry.addData("FlyWheel Motor 2 Power", flyWheelMotor2.getPower());
    }

}
