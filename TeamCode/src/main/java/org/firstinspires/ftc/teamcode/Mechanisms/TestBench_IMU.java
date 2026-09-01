package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;

public class TestBench_IMU {
     private IMU imu;

     public void init(HardwareMap hwp){
         imu = hwp.get(IMU.class, "imu");

         RevHubOrientationOnRobot imuOrientation = new RevHubOrientationOnRobot(
                 RevHubOrientationOnRobot.LogoFacingDirection.UP,
                 RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
         );
         imu.initialize(new IMU.Parameters(imuOrientation));
     }

     public double getYaw(AngleUnit unit){
         return imu.getRobotYawPitchRollAngles().getYaw(unit);
     }
}

