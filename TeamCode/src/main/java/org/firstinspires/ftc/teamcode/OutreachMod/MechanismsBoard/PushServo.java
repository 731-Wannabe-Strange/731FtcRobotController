package org.firstinspires.ftc.teamcode.OutreachMod.MechanismsBoard;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PushServo {
    public Servo pushServo;
    public double position;

    private ElapsedTime wait = new ElapsedTime();

    public void init(HardwareMap hardwareMap) {
        pushServo = hardwareMap.servo.get("servo");
        pushServo.setPosition(0.0);
        position = 0.0;
    }

    private void setPosition(double newPosition) {
        position = 1.0;
        pushServo.setPosition(position);
    }

    public void kick()
    {
        wait.reset();
        setPosition(1.0);
        while(wait.seconds() <= 1)
        {
            continue;
        }
        setPosition(0.0);

    }

    public void update() {

    }

}
