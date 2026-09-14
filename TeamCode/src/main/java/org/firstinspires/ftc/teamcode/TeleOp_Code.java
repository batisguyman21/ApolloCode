package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Imu;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;


@TeleOp(name = "TeleOp BioBuzz", group = "TeleOp_Tests")
public class TeleOp_Code extends LinearOpMode {
    Imu imu = new Imu();
    LimeLight limeLight = new LimeLight();
    ArcadeDrive drive = new ArcadeDrive();


    @Override
    public void runOpMode() {
        double throttle, spin;
        limeLight.init(hardwareMap, imu); // initialise Limelight et Imu
        limeLight.start();
        drive.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            limeLight.update(imu);
            throttle = -gamepad1.left_stick_y;
            spin = gamepad1.left_stick_x;
            drive.drive(throttle, spin);
            
//            telemetry.addData("Distance A mur", limeLight.getDistanceToAprilTag(limeLight.getLlResults().getTa()));
            //executer un code dependamment de l'etat choisie en haut
        }
        drive.drive(0,0);
    }
}
