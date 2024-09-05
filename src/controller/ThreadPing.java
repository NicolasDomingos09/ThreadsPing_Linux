package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {

	String[] commandArr;
	
	public ThreadPing(String[] commandArr) {
		this.commandArr = commandArr;
	}
	
	@Override
	public void run() {
		callProcess(commandArr);
	}
	
	private void callProcess(String[] commandArr) {

		try {
			Process process = Runtime.getRuntime().exec(commandArr);
			InputStream retorno = process.getInputStream();
			InputStreamReader leitor = new InputStreamReader(retorno);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			int size = commandArr.length;
			while(linha != null) {
				String type = "";
				if(linha.contains("ms")) {
					String[] ping = linha.split("e=");
					if(commandArr[size-1].contains("google")) {
						System.out.println("Google - ping: " + ping[1]);
						type = "Google ";
					}
					else if (commandArr[size-1].contains("uol")) {
						System.out.println("UOL - ping: " + ping[1]);
						type = "UOL ";
					}
					else if (commandArr[size-1].contains("terra")) {
						System.out.println("Terra - ping: " + ping[1]);
						type = "Terra ";
					}
				}
				
				if(linha.contains("rtt")) {
					String[] lastLine = linha.split("=");
					String avg = lastLine.toString();
					String[] avgNums = avg.split("/");
					
					Thread.sleep(1500);
					
					System.out.println(type + "Média: " + avgNums[1] + "ms");
				}
				
				linha = buffer.readLine();
			}
			
			retorno.close();
			leitor.close();
			buffer.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
