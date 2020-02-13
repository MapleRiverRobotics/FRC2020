/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 * 
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    WPI_VictorSPX shooterController1 = new WPI_VictorSPX(RobotMap.shooterMotor1);
    WPI_VictorSPX shooterController2 = new WPI_VictorSPX(RobotMap.shooterMotor2);
    WPI_VictorSPX shooterController3 = new WPI_VictorSPX(RobotMap.shooterMotor3);
    WPI_VictorSPX shooterController4 = new WPI_VictorSPX(RobotMap.shooterMotor4);

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void Shoot() {
        shooterController1.set(-0.8);
        shooterController2.set(0.8);
        shooterController3.set(-0.8);
        shooterController4.set(-0.8);
    }

    public void Stop(){
        shooterController1.set(0);
        shooterController2.set(0);
        shooterController3.set(0);
        shooterController4.set(0);
    }
}
