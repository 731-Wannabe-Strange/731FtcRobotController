package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {
    public DcMotor transferMotor;

    public void init(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotor.class, "transfer");
        transferMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
public void transfer(double power) {
        transferMotor.setPower(power);
    }
}
