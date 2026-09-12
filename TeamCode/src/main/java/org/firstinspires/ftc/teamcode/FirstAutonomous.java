package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "Autonomous_BioBuzz", group = "Autonomous")
public class FirstAutonomous extends LinearOpMode{

        enum Etats{
            //ecrire les objectives/but dun section du code
            DefenseBlue,
            AttackBlue,
            DefenseRed,
            AttackRed
        };

    Etats etat;

        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();

        @Override
        public void runOpMode() {
            while (!isStarted() && !isStopRequested()){
                if (gamepad1.aWasReleased()){
                    etat = Etats.DefenseBlue;
                } else if (gamepad1.xWasReleased()) {
                    etat = Etats.AttackBlue;
                } else if (gamepad1.bWasReleased()) {
                    etat = Etats.DefenseRed;
                } else if (gamepad1.yWasReleased()) {
                    etat = Etats.AttackRed;
                }
            }

            waitForStart();
            runtime.reset();
        }
}
