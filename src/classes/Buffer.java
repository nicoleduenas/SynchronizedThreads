package classes;

import java.util.ArrayList;

public class Buffer {

	private ArrayList<Mensaje> buff;
	private int n;
	private Object lleno;
	private Object vacio;
	public Buffer(int n){
		this.n=120;
		lleno= new Object();
		vacio=new Object();
		buff= new ArrayList<Mensaje>();
	}
	//dormir y despertar cliente
	public void almacenar(Mensaje msg){
		synchronized (lleno){
			while(buff.size()== n){
				try{
					lleno.wait();
				} catch(Exception e){
						System.out.println("Error al almacenar en el Buffer.");
				}
			}
		}
		synchronized (this) {
			buff.add(msg);}
		synchronized (vacio) {
			vacio.notify();
		}
		System.out.println("Mensaje almacenado");
		//while!rta->sleep 
	}
	public synchronized Mensaje retirar(){
		synchronized (vacio){
			while(buff.size()== 0){
				try{
					//cuando incluya wait()->()sync
					vacio.wait();
				} catch (InterruptedException e){
					System.out.println("Error al retirar del Buffer.");
				}
			}
		}
		Mensaje msg;
		synchronized (this) {msg= (Mensaje) buff.remove(0);}
		synchronized (lleno) { lleno.notify();}
		System.out.println("Mensaje retirado");

		return msg;
	}
	public boolean buscarId(int pId){
		for (Mensaje act: buff) {
			if(act.getMsg()==pId) return true;
		}
		return false;
	}


}
