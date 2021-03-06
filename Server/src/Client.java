
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.InetAddress;

public class Client {
    public String name;
    public Socket clientSocket;
    
	BufferedWriter out;
	Scanner in;
	
	public Client() { // It will automatically bind connection with server on localhost and on port which was written to console
        int port = 25555;
		while(true) {
			try {
				//port = Integer.parseInt(sc.nextLine().toString());
				port = 25555;
				break;
			} catch(Exception e) {
				System.out.println("Wrong input!");
			}
		}

        
        try {
            this.clientSocket = new Socket(String.valueOf(InetAddress.getLocalHost().getHostAddress()), port);
            this.in = new Scanner(clientSocket.getInputStream());
        }catch (UnknownHostException e){
            e.printStackTrace();	
        }catch (IOException e) {
            e.printStackTrace();
        }
        startListener(in);
    }
	
	private void startListener(final Scanner in) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("Client");
				while(true) {
				//	System.out.println("Lets chack server message...");
					String s = "";
					try {
						s = in.nextLine();
					//	System.out.println("Server message detected");
					}catch(Exception e) {
						System.out.println("Connection interupted " + e);
						System.exit(0);
						break;
					}

					if(s != null) {
						//System.out.println("Message -> " + s);
					}
				}
			}
		});
		thread.start();
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			    try {
			    	out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					out.write("server name clientName" + "\r\n");
					out.flush();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					out.write("server findTheMatch" + "\r\n");
					out.flush();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					out.write("ready" + "\r\n");
					out.flush();
					
			    } catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
}
