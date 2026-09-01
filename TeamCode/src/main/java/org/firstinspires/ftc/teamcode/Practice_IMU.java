package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Mechanisms.TestBench_IMU;

@TeleOp
public class Practice_IMU extends OpMode {
    TestBench_IMU bench = new TestBench_IMU();
    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (bench.getYaw(AngleUnit.DEGREES) > 0.5 || bench.getYaw(AngleUnit.RADIANS) < -0.5){

        }
        telemetry.addData("Heading", bench.getYaw(AngleUnit.RADIANS));
        telemetry.update();
    }
}
