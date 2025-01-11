package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Ascent {

    private DcMotor ascender;

    private Gamepad manipulator;
    private Gamepad prevManipulator;

    int position;

    public Ascent(DcMotor ascent, Gamepad manipulator) {
        this.ascender = ascent;
        this.manipulator = manipulator;
        this.prevManipulator = new Gamepad();
        position = 0;
    }

    public void ascend() {
        if (manipulator.a && !prevManipulator.a){
            position++;
            if(position % 2 == 0){
                ascender.setPower(0);
            }else if(position % 4 == 1){
                ascender.setPower(1);
            }else if(position% 4 == 3){
                ascender.setPower(-1);
            }

        }
        prevManipulator.copy(manipulator);

    }
}
