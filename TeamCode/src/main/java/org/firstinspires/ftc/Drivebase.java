package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class Drivebase {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    private double strafe = 1.1;

    Gamepad driver;

    public Drivebase(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, Gamepad driver) {
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;

        this.driver = driver;
    }

    public void drive() {
        double y = -driver.left_stick_y; // Remember, Y stick value is reversed
        double x = driver.left_stick_x * strafe; // Counteract imperfect strafing
        double rx = driver.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // Vary the speed multiplier based on the left trigger
        double multiplier = 1-driver.left_trigger;

        double frontLeftPower = ((y + x + rx) / denominator) * multiplier;
        double backLeftPower = ((y - x + rx) / denominator) * multiplier;
        double frontRightPower = ((y - x - rx) / denominator) * multiplier;
        double backRightPower = ((y + x - rx) / denominator) * multiplier;

        fl.setPower(-frontLeftPower);
        bl.setPower(-backLeftPower);
        fr.setPower(frontRightPower);
        br.setPower(backRightPower);
    }
}
