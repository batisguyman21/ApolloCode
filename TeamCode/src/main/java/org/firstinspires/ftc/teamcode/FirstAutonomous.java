package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Mechanisms.ArcadeDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Imu;
import org.firstinspires.ftc.teamcode.Mechanisms.LimeLight;

import java.util.HashMap;
import java.util.Set;

@Autonomous(name = "Autonomous_BioBuzz", group = "Autonomous")
public class FirstAutonomous extends LinearOpMode{
    LimeLight limeLight;
    Imu imu;
    ArcadeDrive drive;

//    HashMap<Integer,Integer> pair = new HashMap<>();
    enum Etats{
            // écrire les objectives/but dun section du code
            DefenseBlue,
            AttackBlue,
            DefenseRed,
            AttackRed
    }

    Etats etat;

        // Declare OpMode members.
        private final ElapsedTime runtime = new ElapsedTime();

        @Override
        public void runOpMode() {
//            pair.put(8,0);
//            pair.put(7,2);
//            pair.put(5,2);
//            pair.put(3,3);
//            pair.put(2,4);
//            pair.put(0,5);
            imu.init(hardwareMap);
            drive.init(hardwareMap);
            limeLight.init(hardwareMap, imu);
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
            telemetry.addData("Couleur", (etat.equals(Etats.AttackRed) || etat.equals(Etats.DefenseRed))?"Rouge":"Bleu");
            telemetry.addData("Mode", (etat.equals(Etats.AttackRed) || etat.equals(Etats.AttackBlue))?"Attack":"Defense");

            waitForStart();
            runtime.reset();
        }
}
