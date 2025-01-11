package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(group="teleop")
public class Tele extends OpMode {

    private org.firstinspires.ftc.teamcode.Drivebase drivebase;
    private org.firstinspires.ftc.teamcode.Ascent ascent;
    private org.firstinspires.ftc.teamcode.Aperture aperture;
    private org.firstinspires.ftc.teamcode.HorizontalSlides horizontalSlides;
    private org.firstinspires.ftc.teamcode.VerticalSlides verticalSlides;
    private org.firstinspires.ftc.teamcode.Robot robot;
    private Gamepad prevManipulator;
    private Gamepad manipulator;

    @Override
    public void init() {
        drivebase = new org.firstinspires.ftc.teamcode.Drivebase(hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br"), gamepad1);
        //intake = new Intake(hardwareMap.dcMotor.get("intake"), gamepad1, hardwareMap.get(NormalizedColorSensor.class, "color"), hardwareMap.get(Servo.class, "rotater"));
        ascent = new org.firstinspires.ftc.teamcode.Ascent(hardwareMap.dcMotor.get("ascent"), gamepad1);
        //aperture = new Aperture(hardwareMap.get(CRServo.class, "leftWheel"), hardwareMap.get(CRServo.class, "rightWheel"), hardwareMap.get(Servo.class, "retainer"), hardwareMap.get(Servo.class, "flipper"), hardwareMap.get(Servo.class, "retracter"), gamepad2);
        aperture = new org.firstinspires.ftc.teamcode.Aperture(hardwareMap.get(CRServo.class, "leftWheel"), hardwareMap.get(CRServo.class, "rightWheel"), hardwareMap.get(Servo.class, "flipper"), gamepad1);
        //horizontalSlides = new HorizontalSlides(hardwareMap.dcMotor.get("extender"), gamepad2);
        //verticalSlides = new VerticalSlides(hardwareMap.dcMotor.get("lifter1"),hardwareMap.dcMotor.get("lifter2"), gamepad2);
        //robot = new Robot(aperture, horizontalSlides,verticalSlides);
        prevManipulator = new Gamepad();
    }

    @Override
    public void loop() {

//        Controller buttons
//        right joystick = vertical slides
//        left joystick = horizontal slides
//        triggers: Intake/outake wheels
//        y: retainer
//        x: intake flipepr
//        a: ascent
//        up dpad = high basket;
//        down dpad = vertically retract fully
//        left dpad: horizontal slides re/detract
//        left bumper: automatically do high basket
//        right bumper: automatically do specimen?
        drivebase.drive();
        //intake.run();
        //telemetry.addLine("  " + intake.colorDetection());
        //telemetry.update();
        ascent.ascend();
        aperture.aperture();
        //aperture.retain();
        aperture.flip();
        //aperture.retracter();
        //horizontalSlides.move();
        //horizontalSlides.PID();
        //verticalSlides.move();
        //verticleSlides.PID();
        //robot.transfer(gamepad2, prevManipulator);
        prevManipulator.copy(gamepad2);
    }
}
