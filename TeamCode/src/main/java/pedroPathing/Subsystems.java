package pedroPathing;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Subsystems {
    DcMotor lifter1;
    DcMotor lifter2;
    DcMotor horizontalSlides;
    
    CRServo wheel1;
    CRServo wheel2;
    Servo flipper;
    Servo rotater;
    Servo retainer;

    ElapsedTime timeH;
    ElapsedTime timeV1;
    ElapsedTime timeV2;
    double prevErrorH;
    double prevErrorV1;
    double prevErrorV2;
    public Subsystems(DcMotor lifter1, DcMotor lifter2,    DcMotor horizontalSlides,
    CRServo wheel1,    CRServo wheel2,    Servo flipper,    Servo rotater,
   Servo retainer){
        //TODO: finish constructor        
    }
    public void PID(double target, double kp, double kd){
        double current = horizontalSlides.getCurrentPosition();
        double error = target - current;

        double d = (error - prevErrorH) / timeH.seconds();

        horizontalSlides.setPower(kp * error + kd * d);

        prevErrorH = error;

        timeH.reset();
    }

    public void PIDV1(double target, double kp, double kd){
        double current = lifter1.getCurrentPosition();
        double error = target - current;

        double d = (error - prevErrorV1) / timeV1.seconds();

        lifter1.setPower(kp * error + kd * d);

        prevErrorV1 = error;

        timeV1.reset();
    }

    public void PIDV2(double target, double kp, double kd){
        double current = lifter2.getCurrentPosition();
        double error = target - current;

        double d = (error - prevErrorV2) / timeV2.seconds();

        lifter2.setPower(kp * error + kd * d);

        prevErrorV2 = error;

        timeV2.reset();
    }

    public void flipDown(){
        flipper.setPosition(0);
    }

    public void flipUp() {
        flipper.setPosition(1);
    }

    public void intake(){
        wheel1.setPower(1);
        wheel2.setPower(-1);
    }
    public void outake(){
        wheel1.setPower(-1);
        wheel2.setPower(1);
    }
    public void stopIntaking(){
        wheel1.setPower(0);
        wheel2.setPower(0);
    }
    public void score(){
        retainer.setPosition(1);
    }
    public void resetRetainer() {
        retainer.setPosition(0);
    }
}
