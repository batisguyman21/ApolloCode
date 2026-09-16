package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoAlignTurret {
    private DcMotor turret;

    private double kP = 0.0001; // to be changed after tuning
    private double kD = 0.0000; // to be changed after tuning

    private double goal = 0;
    private double lastError  = 0;
    private double angleTolerence = 0.25;
    private final double MAX_POWER  = 0.4;
    private double power = 0;

    private ElapsedTime timer = new ElapsedTime();

    public void init(HardwareMap hwmap, Telemetry telem){
        turret = hwmap.get(DcMotor.class, "turret");
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void update(LLResult result){
        double deltaTime = timer.seconds();
        timer.reset();

        if(result == null || !result.isValid()){
            turret.setPower(0);
            lastError=0;
            return;
        }

        // ------------ Start PD Controller -------------
        double error = goal - result.getTx();
        double pTerm = error * kP;

        double dTerm =0;
        if(deltaTime > 0){
            dTerm = ((error - lastError) /deltaTime) * kD;
        }

        if(Math.abs(error) < angleTolerence) {
            power = 0;
        }else {
            power = Range.clip(pTerm + dTerm, -MAX_POWER, MAX_POWER);
        }

        // Saftey Checks
        // ...
        turret.setPower(power);
        lastError = error;
    }

    public double getkD() {
        return kD;
    }
    public double getkP() {
        return kP;
    }
    public void setkP(double newkP) {
        this.kP = newkP;
    }
    public void setkD(double newkD) {
        this.kD = newkD;
    }
    public void resetTimer(){
        timer.reset();
    }
}
