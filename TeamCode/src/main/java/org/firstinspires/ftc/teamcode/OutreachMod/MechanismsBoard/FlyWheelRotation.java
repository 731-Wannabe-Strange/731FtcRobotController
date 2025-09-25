package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlyWheelRotation {
    public DcMotorEx flyWheelMotor;

    public PushServo servo = new PushServo();
    public static final int RESTING_MOTION = 0;
    public static final int SPINNING_MOTION = 1;

    private FlyWheelStates currentFlyWheelState = FlyWheelStates.RESTING;

    public enum FlyWheelStates {
        RESTING,
        SPINNING,
        KICK
    }


    public void init(HardwareMap hardwareMap) {
        flyWheelMotor = (DcMotorEx) hardwareMap.dcMotor.get("flywheelr");
        flyWheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flyWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flyWheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flyWheelMotor.setDirection(DcMotor.Direction.REVERSE);
        flyWheelMotor.setPower(RESTING_MOTION);
        flyWheelMotor.setVelocityPIDFCoefficients(1, 0, 0, 0);
    }

private void evaluateFlyWheelState() {
        switch (currentFlyWheelState) {
            case RESTING:
                flyWheelMotor.setPower(RESTING_MOTION);
                break;
            case SPINNING:
                flyWheelMotor.setPower(SPINNING_MOTION);
                break;
            case KICK:
                servo.kick();
                toggleFlyWheelState();
                break;
        }
}

    public void toggleFlyWheelState() {
        switch (currentFlyWheelState) {
            case RESTING:
                currentFlyWheelState = FlyWheelStates.SPINNING;
                break;
            case SPINNING:
                currentFlyWheelState = FlyWheelStates.KICK;
                break;
            case KICK:
                currentFlyWheelState = FlyWheelStates.RESTING;
                break;
            default:
                flyWheelMotor.setPower(RESTING_MOTION);
                break;
        }
        evaluateFlyWheelState();
    }

    public void update() {
        // Empty for now
    }

}
