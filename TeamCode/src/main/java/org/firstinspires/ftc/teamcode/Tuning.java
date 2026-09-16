package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.UtilityOctoQuadConfigMenu;
import org.firstinspires.ftc.teamcode.Mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.AutoAlignTurret;
import org.firstinspires.ftc.teamcode.Mechanisms.Imu;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;

public class Tuning extends OpMode {
    Imu imu = new Imu();

//     uncomment/comment if limelight is present or not
    LimeLight limeLight = new LimeLight();
    AutoAlignTurret turret = new AutoAlignTurret();
    ArcadeDrive drive = new ArcadeDrive();
    double[] steps = new double[]{0.0000001,0.000001,0.0001,0.0001,0.001,0.01};
    int step = 0;
    double throttle, spin;
    @Override
    public void init() {
        limeLight.init(hardwareMap, imu); // initialise Limelight et Imu
        limeLight.start();
        turret.init(hardwareMap,telemetry);
        drive.init(hardwareMap);
        drive.drive(0, 0);
    }

    @Override
    public void loop() {
        limeLight.update(imu); // uncomment/comment if limelight is present or not
        turret.update(limeLight.getLlResults());
        throttle = -gamepad1.left_stick_y;
        spin = gamepad1.left_stick_x;
        drive.drive(throttle, spin);

        if (gamepad1.bWasReleased()){
            step = (step + 1)% steps.length;
        }

        // Left and Right D-pad, Adjusts kP
        if (gamepad1.dpadLeftWasPressed()){
            turret.setkP(turret.getkP() - steps[step]);
        }
        if (gamepad1.dpadRightWasPressed()){
            turret.setkP(turret.getkP() + steps[step]);
        }
        // Up and down D-pad, Adjusts kD
        if (gamepad1.dpadUpWasPressed()){
            turret.setkD(turret.getkD() + steps[step]);
        }
        if (gamepad1.dpadDownWasPressed()){
            turret.setkD(turret.getkD() - steps[step]);
        }

        telemetry.addLine("-------------------------------------------------");
        telemetry.addData("Tuning kP", "%.9f (D-Pad L/R)", turret.getkP());
        telemetry.addData("Tuning kD", "%.9f (D-Pad U/D)", turret.getkD());
        telemetry.addData("Step Size", "%.9f (B Button)", steps[step]);

    }
}
