package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OutreachMod.TeleOps.OutreachTeleOp;


public class ArmRotation extends OutreachTeleOp {
    private DcMotorEx armMotor1;
    private DcMotorEx armMotor2;
    private static final int MIN_POSITION = -50;
    private static final int MAX_POSITION = 1000;
    private static final double ARM_POWER = 0.7;

    ElapsedTime timer = new ElapsedTime();
    double targetPosition;
    public double liftTime = 8;
    private final double ARM_SPEED = Math.PI/liftTime;

    public ArmStates currentArmState = ArmStates.HOLDING;
    public ArmStates previousArmState = ArmStates.HOLDING;

    public enum ArmStates {
        HOLDING,
        MOVING_UP,
        MOVING_DOWN,
        MANUAL_STOP
    }

    private double currentMotor1TargetPosition, currentMotor2TargetPosition = 0;


    public void init(HardwareMap hardwareMap) {
        armMotor1 = (DcMotorEx) hardwareMap.dcMotor.get("armMotor1");
        armMotor2 = (DcMotorEx) hardwareMap.dcMotor.get("armMotor2");

        armMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armMotor1.setTargetPosition(2000*((int)Math.floor(currentMotor1TargetPosition)));
        armMotor2.setTargetPosition(2000*((int)Math.floor(currentMotor2TargetPosition)));

        armMotor1.setDirection(DcMotor.Direction.FORWARD);
        armMotor2.setDirection(DcMotor.Direction.REVERSE);

        armMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor1.setPower(0);
        armMotor2.setPower(0);

    }

    public void setArmState(boolean dPadUp, boolean dPadDown) {
        switch (currentArmState)
        {
            case HOLDING:
                if(previousArmState == ArmStates.MOVING_UP && dPadDown)
                {
                    previousArmState = currentArmState;
                    currentArmState = ArmStates.MOVING_DOWN;
                    targetPosition = 0;
                }
                else {
                    if (gamepad1.dpad_up) {
                        timer.reset();
                        previousArmState = currentArmState;
                        currentArmState = ArmStates.MOVING_UP;
                    }
                }
                break;
            case MOVING_UP:
                if(!dPadUp)
                {
                    previousArmState = currentArmState;
                    currentArmState = ArmStates.HOLDING;
                }
                else
                {
                    targetPosition = ARM_SPEED * timer.seconds();
                }
                break;
            case MOVING_DOWN:
                if(!gamepad1.dpad_down)
                {
                    previousArmState = currentArmState;
                    currentArmState = ArmStates.HOLDING;
                }
                else
                {
                    targetPosition = -ARM_SPEED * timer.seconds();
                }
                break;
            case MANUAL_STOP:
                previousArmState = currentArmState;
                armMotor1.setPower(0);
                armMotor2.setPower(0);
            default:
                break;

        }


        // Apply software limits before setting the target
        targetPosition = Math.max(MIN_POSITION, Math.min(targetPosition, MAX_POSITION));

        currentMotor1TargetPosition = targetPosition;
        currentMotor2TargetPosition = targetPosition;

        armMotor1.setTargetPosition(2000*((int)Math.floor(currentMotor1TargetPosition)));
        armMotor2.setTargetPosition(2000*((int)Math.floor(currentMotor2TargetPosition)));

        armMotor1.setPower(ARM_POWER);
        armMotor2.setPower(ARM_POWER);

    }

    public ArmStates getCurrentArmTargetState() {
        return currentArmState;
    }

    public ArmStates getLastArmState() {
        return previousArmState;
    }

    public double getMotor1Position() {
        return armMotor1.getCurrentPosition();
    }

    public double getMotor2Position() {
        return armMotor2.getCurrentPosition();
    }

    public void update() { // Empty for now

    }
}


