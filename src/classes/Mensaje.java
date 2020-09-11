package classes;

public class Mensaje {
	private int msg;
	private String contenido;
	private String respuesta;

	public Mensaje(int mensaje, String contenido){
		setMsg(mensaje);
		this.setContenido(contenido);
	}

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
