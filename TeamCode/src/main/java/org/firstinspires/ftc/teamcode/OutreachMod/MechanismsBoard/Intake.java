package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    public DcMotorEx intake;
    public boolean isRunning;

    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "lff");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setDirection(DcMotor.Direction.FORWARD);
        isRunning = false;
    }

    public void toggle() {
        intake.setPower(isRunning ? 0.0 : 1.0);
        isRunning = !isRunning;
    }

    
}
