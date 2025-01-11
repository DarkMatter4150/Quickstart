package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VerticalSlides {
    private DcMotor lifter1;
    private DcMotor lifter2;

    private Gamepad manipulator;
    private Gamepad prevManipulator;
    ElapsedTime time = new ElapsedTime();

    double prevError1;
    double prevError2;
    double i1 = 0;
    double i2 = 0;
    double t = 0;

    public static final int HIGHBASKET = 0;
    public static final int LOWBASKET = 0;
    public static final int BOTTOM = 0;

    public VerticalSlides(DcMotor lifter1, DcMotor lifter2, Gamepad manipulator){
        this.lifter1 = lifter1;
        this.lifter2 = lifter2;

        lifter1.setDirection(DcMotorSimple.Direction.REVERSE);

        this.manipulator = manipulator;
        this.prevManipulator = new Gamepad();

        resetPID();
    }

    public void move(){
        t+= manipulator.right_stick_y;

        if(manipulator.dpad_up && !prevManipulator.dpad_up) {
            resetPID();
            t = HIGHBASKET;
        }
        if(manipulator.dpad_down && !prevManipulator.dpad_down) {
            resetPID();
            t = BOTTOM;
        }
        lPIDF(t, 0.02, 0, 0, 0);
        rPIDF(t,0.02,0,0, 0);

        prevManipulator.copy(manipulator);
    }

    public void rPIDF(double target, double kp, double ki, double kd, double ff){
        t = target;
        double current1 = lifter1.getCurrentPosition();

        double error1 = target - current1;

        double seconds = time.seconds();

        double d1 = (error1 - prevError1) / seconds;

        i1 += error1 * seconds;

        lifter1.setPower(kp * error1 + ki * i1 + kd * d1 + ff);

        prevError1 = error1;


        time.reset();
    }

    public void lPIDF(double target, double kp, double ki, double kd, double ff){
        t = target;

        double current2 = lifter2.getCurrentPosition();

        double error2 = target - current2;

        double seconds = time.seconds();

        double d2 = (error2 - prevError2) / seconds;

        i2 += error2 * seconds;

        lifter2.setPower(kp * error2 + ki * i2 + kd * d2 +  ff);

        prevError2 = error2;

    }

    public void resetPID(){
        prevError1 = 0;
        prevError2 = 0;
        i1 = 0;
        i2 = 0;
    }

    public void setTarget(int target){
        t = target;
        resetPID();
    }

}
