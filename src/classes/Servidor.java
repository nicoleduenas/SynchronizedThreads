package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Servidor extends Thread {


	private Buffer buffer;
	private Mensaje msg;
	private Boolean on;
	//manda un msg y tiene acceso al buffer
	public Servidor( Buffer pBuff){
		this.buffer=pBuff;
		this.on=true;
	}
	public void responder(){
		while(on) {
			msg = buffer.retirar();
			yield();
			msg.setMsg(msg.getMsg() + 1);
			notifyAll();
		}
	}
	public void run(){
		this.responder();
	}
}
