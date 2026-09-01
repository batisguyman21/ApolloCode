package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class TestBench_LimeLight extends OpMode {
    private Limelight3A limelight;
    private IMU imu;
    @Override
    public void init() {
        limelight =  hardwareMap.get(Limelight3A.class, "limelight");
        imu =  hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot revHubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP, // where the rc logo is facing relative to robot
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD // where the usb is facing relative to robot
        );

        imu.initialize(new IMU.Parameters(revHubOrientation));
    }

    @Override
    public void start() {
        limelight.start(); // if there is a delay comment it out and put it at the end of init.
    }

    @Override
    public void loop() {
        // Update the orientation(yaw) of the limelight so its calculations aren't off
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());

        LLResult llresult = limelight.getLatestResult();

        if (llresult != null && llresult.isValid()) {
            Pose3D pose = llresult.getBotpose_MT2();
            telemetry.addData("pose.x", pose.getPosition().x);
            telemetry.addData("pose.y", pose.getPosition().y);
            telemetry.addData("pose.z", pose.getPosition().z);
        }
        /* The Difference Between Pose3D and llresult:
            llresult is a package sent from the limelight itself
            that you can use to transform what your robot "sees"
            into real life cords (0,0,0) being the center of the arenas floor

            BUT

            Pose3D is the interpreter.
            it takes the data from llresult and organizes it into the cords
            so you don't have to do it yourself. :)
        */
    }
}
