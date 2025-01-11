package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class ObservationZoneAuto extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private ElapsedTime time;

    private boolean started = false;
    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        time = new ElapsedTime();
    }

    @Override
    public void loop(){
        if(!started){
            time = new ElapsedTime();
            started=true;
        }
        if(time.seconds() < 1.8){ // go straight to zone
            strafeRight(0.5);
        }else{
            fl.setPower(0);
            br.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
        }


    }
    public void strafeRight(double power){
        fl.setPower(-power);
        bl.setPower(power);
        fr.setPower(-power);
        br.setPower(power);
    }
}

