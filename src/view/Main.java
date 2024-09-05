package view;

import controller.ControllerOS;
import controller.ThreadPing;

public class Main {

	public static void main(String[] args) {
		
		String[] servers = {"www.uol.com.br", "www.terra.com.br", "www.google.com.br"};
		
		ControllerOS contOS = new ControllerOS();
		

		boolean os = contOS.getOS();
		if(os == true) {
			for(String link : servers) {
				
				String command = "ping -4 -c 10 " + link;
				String[] commandArr = command.split(" ");
				
				Thread t = new ThreadPing(commandArr);
				t.start();	
			}
		}else {
			System.out.println("Tipo de sistema não suportado");
			System.exit(0);
		}
	}
}