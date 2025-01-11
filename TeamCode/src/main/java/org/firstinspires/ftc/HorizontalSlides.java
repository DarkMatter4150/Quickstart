package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HorizontalSlides {
    private DcMotor extender;
    private Gamepad manipulator;
    private Gamepad prevManipulator;

    ElapsedTime time = new ElapsedTime();

    boolean pos = false;
    double prevError;
    double i = 0;
    double t = 0;

    public static final int EXTENDED = 0;
    public static final int RETRACTED = 0;

    public HorizontalSlides(DcMotor extender, Gamepad manipulator){
        this.extender = extender;
        this.manipulator = manipulator;
        resetPID();
        prevManipulator = new Gamepad();
    }

    public void setTarget(int target){
        t = target;
        resetPID();
    }

    public int getPos(){
        return extender.getCurrentPosition();
    }

    public void move(){
        t+=manipulator.left_stick_x;
        PID(t, 0.02, 0,0);
        if(manipulator.dpad_left && !prevManipulator.dpad_left){
            pos=!pos;
            resetPID();
            if(pos)
                t = EXTENDED;
            else
                t = RETRACTED;
        }

        prevManipulator.copy(manipulator);
    }

    public void PID(double target, double kp, double ki, double kd){
        t = target;
        double current = extender.getCurrentPosition();
        double error = target - current;

        double d = (error - prevError) / time.seconds();

        i += error * time.seconds();

        extender.setPower(kp * error + ki * i + kd * d);

        prevError = error;

        time.reset();
    }

    private void resetPID(){
        prevError = 0;
        i = 0;
    }

}
