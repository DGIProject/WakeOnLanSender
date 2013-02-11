package WOL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WOL_class extends JApplet implements ActionListener,ItemListener {
	

	private static final long serialVersionUID = -8154652761516428857L;
	
	JTextField ip;
	JTextField mac;
	JTextField port;
	JLabel ip_text;
	JLabel mac_text;
	JLabel port_text;
	JButton lunch;
	JLabel statu;
	String er;
	
	private Pattern pattern;
    private Matcher matcher;
 
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	public void init() {
		
		setLayout(null);
		ip = new JTextField();
		mac = new JTextField();
		port = new JTextField(5);
		ip_text = new JLabel("IP :");
		mac_text = new JLabel("MAC :");
		port_text = new JLabel("PORT :");
		lunch = new JButton("Lancer");
		statu = new JLabel("Veuillez renplir les champs");
		
		ip_text.setBounds(10, 50, 50, 25);
		ip.setBounds(70, 50, 100, 25);		
		mac_text.setBounds(180, 50, 50, 25);
		mac.setBounds(230, 50, 100, 25);
		port_text.setBounds(330, 50, 50, 25);
		port.setBounds(380, 50, 100,25);
		lunch.setBounds(25, 80, 100, 25);
		statu.setBounds(130, 80, 300, 25);
		
		add(lunch);
		add(ip);
		add(ip_text);
		add(mac);
		add(mac_text);
		add(port);
		add(port_text);
		add(statu);
		
		port.setText("9");
		lunch.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == lunch)
		{
			System.out.println("bla3");
			if (checkValue())
			{
				System.out.println("bla");
				if (validateMac(mac.getText()))
				{					
				Wol(ip.getText(), mac.getText());
				}
				else
				{
					statu.setText("Adresse mac invalide !");
				}
			}
			else
			{
				statu.setText("Ip non valide !");
			}
		}
		else
		{
			
		}
	}

	private void Wol(String ip, String mac) {
		
		String ipStr = ip;
        String macStr = mac;
        //int portint = Integer.parseInt(port); 
        
        try {
            byte[] macBytes = getMacBytes(macStr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            
            InetAddress address = InetAddress.getByName(ipStr);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 9);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            
            statu.setText("Wake-on-LAN packet sent.");

        }
        catch (Exception e) {            
            statu.setText("Failed to send Wake-on-LAN packet" + er);
        }
        
    }
	
	private byte[] getMacBytes(String macStr) {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {        	
        	statu.setText("Invalid MAC address.");
        	er = "Invalid MAC address.";      	
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {            
        	statu.setText("Invalid hex digit in MAC address.");
        	er = "Invalid hex digit in MAC address.";
        }
        return bytes;
    }

	private boolean checkValue() {
		statu.setText("Verrification des valeurs ...");
		return validate(ip.getText());
						
	}
	
	public boolean validate(final String ip){	
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(ip);
		return matcher.matches();	    	    
	    }
	public boolean validateMac(String mac)
	{
		Pattern macPattern = Pattern.compile( "(([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2}" );
		matcher = macPattern.matcher(mac);
		return matcher.matches();		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}

