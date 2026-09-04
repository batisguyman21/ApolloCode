package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ArcadeDrive {
    private DcMotor rightMotor;
    private DcMotor leftMotor;

    public void init(HardwareMap hardwareMap) {
        rightMotor = hardwareMap.get(DcMotor.class, "motor right");
        leftMotor = hardwareMap.get(DcMotor.class, "motor left");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double throttle, double spin){
        double leftSpeed = throttle + spin;
        double rightSpeed = throttle - spin;
        double largest = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if(largest > 1.0){
            leftSpeed /= largest;
            rightSpeed /= largest;
        }
        rightMotor.setPower(rightSpeed);
        leftMotor.setPower(leftSpeed);
    }

    public void turn(double degrees, Imu imu){
        while (degrees != imu.getYaw(AngleUnit.DEGREES)){
            if (degrees > imu.getYaw(AngleUnit.DEGREES)){
                // left forward, and right backwards
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
            }else{
                leftMotor.setPower(-0.5);
                rightMotor.setPower(0.5);
            }
        }
    }
    public void turn(double degrees, Imu imu, double speed){
        while (degrees != imu.getYaw(AngleUnit.DEGREES)){
            if (degrees > imu.getYaw(AngleUnit.DEGREES)){
                // left forward, and right backwards
                leftMotor.setPower(speed);
                rightMotor.setPower(-speed);
            }else{
                leftMotor.setPower(-speed);
                rightMotor.setPower(speed);
            }
        }
    }


}
