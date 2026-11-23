package frc.robot.subsystems;

import frc.robot.Constants.ElevatorConstants;

import static edu.wpi.first.units.Units.Newton;

import java.io.ObjectInputFilter.Config;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//two motors
// Elevator going to be at 20s
public class OurElevatorSubsystem extends SubsystemBase {
    private final SparkMax LeftLiftmotor = new SparkMax(ElevatorConstants.LeftLiftmotorID, MotorType.kBrushless);
    private final SparkMax RightLifmotor_leader = new SparkMax(ElevatorConstants.RightLiftmotorID,
            MotorType.kBrushless);
    private final RelativeEncoder rightMotoEncoder = RightLifmotor_leader.getEncoder();

    private final ProfiledPIDController elevatorController = new ProfiledPIDController(ElevatorConstants.elevatorKp,
            ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd,
            new Constraints(ElevatorConstants.elevatorMaxVelocity, ElevatorConstants.elevatorMaxAcceleration));
    private final ElevatorFeedforward elevatorFeedforward = new ElevatorFeedforward(ElevatorConstants.elevatorKs,
            ElevatorConstants.elevatorKg, ElevatorConstants.elevatorKV,
            ElevatorConstants.elevatorMaxAcceleration);

    public OurElevatorSubsystem() {
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40).closedLoopRampRate(ElevatorConstants.elevatorRampRate);
        //I am lost past here so I won't add more lines until I get it. 
    }

    public double calculate(double velocity) {
        return calculate(velocity);

    }

    private DigitalInput topLimitSwitch = new DigitalInput(0);

    private static final double kp = 1.0;
    private double targetPosition = 0.0;

    // masterandslave = true ?

}
