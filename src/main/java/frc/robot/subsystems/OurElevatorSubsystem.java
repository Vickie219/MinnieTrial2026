package frc.robot.subsystems;
import frc.robot.Constants; 

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//two motors
// Elevator going to be at 20s
public class OurElevatorSubsystem extends SubsystemBase{
private SparkMax LeftLiftmotor;
private SparkMax RightLiftmotor;

public OurElevatorSubsystem() { 

LeftLiftmotor = new SparkMax(Constants.LeftLiftmotorID, null); 
RightLiftmotor = new SparkMax(Constants.RightLiftmotorID, null);
  }

public void LeftLiftmotor(){
    LeftLiftmotor.set(0.5);
} 

public void RightLiftmotor(){ 
    RightLiftmotor.set(0.5);
}

  public double calculate(double velocity){
    return calculate(velocity);

  }

private DigitalInput topLimitSwitch = new DigitalInput(0); 

private static final double kp = 1.0;
private double targetPosition = 0.0;

//masterandslave = true ?


}

