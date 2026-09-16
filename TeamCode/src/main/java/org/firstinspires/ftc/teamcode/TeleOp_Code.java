package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Imu;
// uncomment/comment if limelight is present or not
//import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;


@TeleOp(name = "TeleOp BioBuzz", group = "TeleOp_Tests")
public class TeleOp_Code extends LinearOpMode {
    Imu imu = new Imu();

    // uncomment/comment if limelight is present or not
//    LimeLight limeLight = new LimeLight();
//    AutoAlignTurret turret = new AutoAlignTurret();
    ArcadeDrive drive = new ArcadeDrive();



    @Override
    public void runOpMode() {
        double throttle, spin;
        // uncomment/comment if limelight is present or not
//        limeLight.init(hardwareMap, imu); // initialise Limelight et Imu
//        limeLight.start();
//        turret.init(hardwareMap,telemetry);

        drive.init(hardwareMap);

        telemetry.addLine("Initialization finis!");
        waitForStart();

        while (opModeIsActive()) {
//            limeLight.update(imu); // uncomment/comment if limelight is present or not
//            turret.update(limeLight.getLlResults());

            throttle = -gamepad1.left_stick_y;
            spin = gamepad1.left_stick_x;
            drive.drive(throttle, spin);
            
//            telemetry.addData("Distance A mur", limeLight.getDistanceToAprilTag(limeLight.getLlResults().getTa()));
            //executer un code dependamment de l'etat choisie en haut
        }
        drive.drive(0,0);
    }
}
