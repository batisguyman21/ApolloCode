package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@TeleOp(name = "TeleOp Batis-Leo-Bastien", group = "TeleOp_Tests")
public class TeleOp_Code extends LinearOpMode {
    IMU imu;
    Pose3D pose;
    Limelight3A limelight;
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        double leftspeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
        double rightspeed = gamepad1.left_stick_y - gamepad1.left_stick_x;
        initialize(); // ecrire son code de initialization ici
        limelight.start();
        waitForStart();
        while (opModeIsActive()){

            initLL(); // LimeLight
            // arcade drive?
            leftspeed = gamepad1.left_stick_y + gamepad1.left_stick_x;
            rightspeed = gamepad1.left_stick_y - gamepad1.left_stick_x;

            leftMotor.setPower(leftspeed);
            rightMotor.setPower(rightspeed);
        }
    }

    /*  ******************************
        *      INITIALISATIONS       *
        ******************************
     */
    private void initLL() {
        // LimeLight init
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llresult = limelight.getLatestResult();
        if (llresult != null && llresult.isValid()) {
            pose = llresult.getBotpose_MT2();
        }
    }

    public void initialize(){
        rightMotor = hardwareMap.get(DcMotor.class, "motor right");
        leftMotor = hardwareMap.get(DcMotor.class, "motor left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        imu = hardwareMap.get(IMU.class, "imu");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
    }
    void initIMU(){
        RevHubOrientationOnRobot imuOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );
        imu.initialize(new IMU.Parameters(imuOrientation));
    }
}
