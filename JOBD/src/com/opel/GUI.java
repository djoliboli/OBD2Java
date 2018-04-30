import javax.swing.* ;
import java.awt.*;

public class GUI {

	public GUI() {
		// TODO Auto-generated constructor stub
	//Window
	JFrame MainWindow = new JFrame("OBD2 Scanner");
	MainWindow.setSize(816, 518);
	MainWindow.setVisible(true);
	
	//WindowManager
	JPanel contentPanel = new JPanel();
	contentPanel.setOpaque(true);
	contentPanel.setBackground(Color.WHITE);
	contentPanel.setLayout(null);
	
	//Title
	JLabel Title = new JLabel("OBD-2 Dashboard", JLabel.CENTER);
	Title.setSize(800, 80);
	Title.setLocation(0, 0);
	Title.setFont(new Font("Serif", Font.PLAIN, 28));
	Title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	//MainShit noch nicht implementiert
	JPanel mainPanel = new JPanel();
	mainPanel.setBackground(Color.RED);
	mainPanel.setPreferredSize(new Dimension(800, 390));
	mainPanel.setLocation(0, 80);
	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	mainPanel.setLayout(null);
	mainPanel.setOpaque(true);
	mainPanel.setVisible(true);
	
	// FooterLinks
	JLabel FooterLeft = new JLabel("Version 0.0.1", JLabel.LEFT);
	FooterLeft.setSize(300, 10);
	FooterLeft.setFont(new Font("Serif", Font.PLAIN, 8));
	FooterLeft.setLocation(0, 470);
	FooterLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	//FooterMitte
	JLabel FooterCenter = new JLabel("Studienarbeit an der DHBW Mannheim", JLabel.CENTER);
	FooterCenter.setSize(200, 10);
	FooterCenter.setFont(new Font("Serif", Font.PLAIN, 8));
	FooterCenter.setLocation(300, 470);
	FooterCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	//FooterRight
	JLabel FooterRight = new JLabel("© Maximilian Olbort, Jonathan Seibel, Marvin Meinhard", JLabel.RIGHT);
	FooterRight.setSize(300, 10);
	FooterRight.setFont(new Font("Serif", Font.PLAIN, 8));
	FooterRight.setLocation(500, 470);
	FooterRight.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	//weise dem Frame das Panel zu
	contentPanel.setPreferredSize(new Dimension(800,480));
	contentPanel.add(Title);
	contentPanel.add(FooterLeft);
	contentPanel.add(FooterCenter);
	contentPanel.add(FooterRight);
	contentPanel.add(mainPanel);
	contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	contentPanel.setLayout(null);
	//MainWindow.setContentPane(contentPanel);
	MainWindow.getContentPane().add(contentPanel);
	
	MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		GUI Test = new GUI();
	}
	
}
