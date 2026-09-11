package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Imu;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;


@TeleOp(name = "TeleOp 2026-2027", group = "TeleOp_Tests")
public class TeleOp_Code extends LinearOpMode {
    Imu imu = new Imu();
    LimeLight limeLight = new LimeLight();
    ArcadeDrive drive = new ArcadeDrive();

//    enum Etat{
//        //ecrire les objectives/but dun section du code
//        Attendre
//    };

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
            
            telemetry.addData("Distance A mur", limeLight.getDistanceToAprilTag(limeLight.getLlResults().getTa()));
            //executer un code dependamment de l'etat choisie en haut
        }
    }
}
