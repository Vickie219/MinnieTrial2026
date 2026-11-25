package frc.robot.subsystems;


import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import au.grapplerobotics.interfaces.LaserCanInterface.RangingMode;
import au.grapplerobotics.interfaces.LaserCanInterface.RegionOfInterest;
import au.grapplerobotics.interfaces.LaserCanInterface.TimingBudget;
import frc.robot.Constants.ElevatorConstants;

import static edu.wpi.first.units.Units.Newton;




import java.io.ObjectInputFilter.Config;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.hal.simulation.RoboRioDataJNI;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.simulation.DIOSim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;


//import static au.grapplerobotics.interfaces.LaserCanInterface

//two motors
// Elevator going to be at 20s
public class OurElevatorSubsystem extends SubsystemBase {
    //setup 
    private final SparkMax LeftLiftmotor = new SparkMax(ElevatorConstants.LeftLiftmotorID, MotorType.kBrushless);
    private final SparkMax RightLifmotor_leader = new SparkMax(ElevatorConstants.RightLiftmotorID,
            MotorType.kBrushless);
    
    private final SparkMaxSim LeftSimMotor = new SparkMaxSim(LeftLiftmotor, null);
    private final RelativeEncoder rightMotoEncoder = RightLifmotor_leader.getEncoder();

    private final ProfiledPIDController elevatorController = new ProfiledPIDController(ElevatorConstants.elevatorKp,
            ElevatorConstants.elevatorKi, ElevatorConstants.elevatorKd,
            new Constraints(ElevatorConstants.elevatorMaxVelocity, ElevatorConstants.elevatorMaxAcceleration));
    private final ElevatorFeedforward elevatorFeedforward = new ElevatorFeedforward(ElevatorConstants.elevatorKs,
            ElevatorConstants.elevatorKg, ElevatorConstants.elevatorKV,
            ElevatorConstants.elevatorMaxAcceleration);

     private final LaserCan m_elevatorLaserCan = new LaserCan(0);
    private final LaserCanSim m_elevatorLaserCanSim = new LaserCanSim(0);
    private final RegionOfInterest m_laserCanROI = new RegionOfInterest(0, 0, 16, 16);
    private final TimingBudget m_laserCanTimingBudget = TimingBudget.TIMING_BUDGET_20MS;
    private final Alert m_laserCanFailure = new Alert("LaserCAN failed to configure.",
            AlertType.kError);
    private final DigitalInput m_limitSwitchLow = new DigitalInput(9);
    private DIOSim m_limitSwitchLowSim = null;

    private final ElevatorSim m_elevatorSim = null; // When the robot is not in the simulation
    
    // private final LaserCanSim m_elevatorLaserCanSim = new LaserCanSim(0);
    public OurElevatorSubsystem() {
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40).closedLoopRampRate(ElevatorConstants.elevatorRampRate);

        if (RobotBase.isSimulation()) {
            m_elevatorSim = new ElevatorSim(m_elevatorGearbox,
                    ElevatorConstants.kElevatorGearing,
                    ElevatorConstants.kElevatorCarrigeMass,
                    ElevatorConstants.kElevatorDrumRadius,
                    ElevatorConstants.kElevatorMaxHeightMeters,
                    ElevatorConstants.k,
                    true,
                    0.0,
                    0.02,
                    0.0);

            m_limitSwitchLowSim = new DIOSim(m_limitSwitchLow);//

            SmartDashboard.putData("Elevator Low limit Switch", m_limitSwitchLow);
        }
        //I am lost past here so I won't add more lines until I get it. 
    }

    
        try {
            m_elevatorLaserCanSim.setRangingMode(RangingMode.LONG);
        } catch (Exception e) {
            m_laserCanFailure.set(true);
        }

 public void simulationPeriodic() {
        //set input(voltage)
        m_elevatorSim.setInput(m_motorSim.getAppliedOutput() * RoboRioSim.getVInVoltage());

        //update-every 20 milliseconds
        m_elevatorSim.update(0.02);

        m_motorSim.iterate(Elevator.convertDistanceToRotations(Meters.of(m_elevatorSim.getVelocityMetersPerSecond()))
                        .per(Second).in(RPM),
                RoboRioSim.getVInVoltage(),
                0.020);

        RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(m_elevatorSim.getCurrentDrawAmps()));

         m_elevatorLaserCanSim.setMeasurementFullSim(new Measurement(
                LASERCAN_STATUS_VALID_MEASUREMENT,
                (int) (Math.floor(Meters.of(m_elevatorSim.getPositionMeters()).in(Millimeters)) +
                        ElevatorConstants.kLaserCANOffset.in(Millimeters)),
                0,
                true,
                m_laserCanTimingBudget.asMilliseconds(),
                m_laserCanROI
        ));
        
    public double calculate(double velocity) {
        return calculate(velocity);


    }

    private DigitalInput topLimitSwitch = new DigitalInput(0);

    private static final double kp = 1.0;
    private double targetPosition = 0.0;

    // masterandslave = true ?

}
