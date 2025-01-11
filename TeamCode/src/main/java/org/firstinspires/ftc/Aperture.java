package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Aperture {
    private CRServo leftWheel;
    private CRServo rightWheel;
    // private Servo retainer;
    private Servo flipper;
    //private Servo retracter;
    private Gamepad manipulator;
    private Gamepad prevManipulator;

    //private boolean retainerDirection = false;
    private boolean flipDirection = false;
    public Aperture(CRServo leftWheel, CRServo rightWheel, Servo flipper, Gamepad manipulator){
        /*, Servo retainer, Servo flipper, Servo retracter, Gamepad manipulator*/
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
        //this.retainer = retainer;
        this.flipper = flipper;
        //this.retracter = retracter;
        this.manipulator = manipulator;
        this.prevManipulator = new Gamepad();
    }

    public void aperture(){
        double power = manipulator.left_trigger - manipulator.right_trigger;
        leftWheel.setPower(power);
        rightWheel.setPower(-power);
    }
    //    public void retain(){
//        if(manipulator.y && !prevManipulator.y){
//            retainerDirection = !retainerDirection;
//            if(retainerDirection)
//                retainer.setPosition(1);
//            else
//                retainer.setPosition(0);
//        }
//    }
    public void eject(){
        leftWheel.setPower(1);
        rightWheel.setPower(-1);
    }
    public void stop(){
        leftWheel.setPower(0);
        rightWheel.setPower(0);
    }
    public void flipUp(){
        flipDirection = true;
        flipper.setPosition(1);
    }
    public void flipDown(){
        flipDirection = false;
        flipper.setPosition(0);
    }
    public double getPos(){
        return flipper.getPosition();
    }
    public void flip(){
        if(manipulator.x && !prevManipulator.x){
            flipDirection = !flipDirection;
            if(flipDirection)
                flipUp();
            else
                flipDown();
        }
        prevManipulator.copy(manipulator);
    }

//    public void retracter(){
//        prevManipulator.copy(manipulator);
//
//    }
}
