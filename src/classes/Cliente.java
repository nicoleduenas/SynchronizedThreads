package classes;

import java.util.ArrayList;

public class Cliente extends Thread{
	private int nMensajes;
	private int nEnviados;
	private static int numMsg;
	private Buffer buffy;

	public Cliente(int numMensajes, Buffer pBuffer) {
		nMensajes=numMensajes;
		nEnviados=0;
		numMsg=0;
		buffy=pBuffer;

	}
	public void enviarMensajes(){
		while(nEnviados<nMensajes){
			Mensaje msg = new Mensaje(numMsg, "This is a generic message");

			buffy.almacenar(msg);
			System.out.println("Prueba");
			//waits de cx en buffer
			//despertar y dormir en buffer
			//			synchronized (this) {
			//				numMsg += 2;
			//			}
			//			try {
			//				wait();
			//			} catch (InterruptedException e) {
			//				e.printStackTrace();
			//			}
			while(buffy.buscarId(numMsg-2)){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public void run(){
		this.enviarMensajes();
	}

	public Buffer getBuffy() {
		return buffy;
	}

	public void setBuffy(Buffer buffy) {
		this.buffy = buffy;
	}

}
