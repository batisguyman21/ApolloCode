package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class LimeLight {
    private Limelight3A limelight;
    public Pose3D pose;
    public void init(HardwareMap hwMap, Imu imu){
        limelight = hwMap.get(Limelight3A.class, "limeLight");
        imu.init(hwMap);
    }

    public void update(Imu imu) {
        YawPitchRollAngles orientation = imu.getYawPitchRoll();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llresult = limelight.getLatestResult();
        if (llresult != null && llresult.isValid()) {
            pose = llresult.getBotpose_MT2();
        }
    }

    public Pose3D getPose(){
        return pose;
    }

    public void start(){
        limelight.start();
    }

    public LLResult getLlResults(){
        return limelight.getLatestResult();
    }

    public double getDistanceToAprilTag(double ta){
        //experiment with light at 50cm away, 100, cm away ect until 200
        // in a spreadsheet get the target average, min and max for each of them
        //then go to "MyCurveFit.com"
        //then do x axis title is 50 and y axis is the average for 50cm and same thing for all of them
        //then do fit method, nonlinear and power Curve then copy 'y' in the equation
        double scale; // put y value in here
        return 0; // change this to scale/ta
        //if this doesnt make sense just rewatch Brogan vid
    }
}
