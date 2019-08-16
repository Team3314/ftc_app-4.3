package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.AllRobots.HMap;

public class DifferentialDrivetrain {
    ArrayList<DcMotor> leftMotors = new ArrayList<DcMotor>();
    ArrayList<DcMotor> rightMotors = new ArrayList<DcMotor>();

    public DifferentialDrivetrain(String... motors){
        int side = motors.length / 2;
        for(int m = 0; m < side; m++){
            DcMotor someMotor  = HMap.get(DcMotor.class, motors[m]);
            leftMotors.add(someMotor);
        }

        for(int m = side; m < motors.length; m++){
            DcMotor someMotor  = HMap.get(DcMotor.class, motors[m]);
            someMotor.setDirection(REVERSE);
            rightMotors.add(someMotor);
        }
        leftMotors.get(0).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotors.get(0).setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void move( MoveParameter mP ){
        double leftPower;
        double rightPower;


        leftPower = Range.clip(mP.forward - mP.turn, -1.0, 1.0) ;
        rightPower = Range.clip(mP.forward + mP.turn, -1.0, 1.0) ;

        for(DcMotor M: leftMotors){
            M.setPower(leftPower);
        }

        for(DcMotor M: rightMotors){
            M.setPower(rightPower);
        }
    }

    public double getEncoderValue(){
        return leftMotors.get(0).getCurrentPosition();
    }
}
