package classes;

import classes.Buffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static int tamanoBuffer;
    private static int numeroClientes;
    private static int numeroServidores;
    private static int numeroMensajes;

    public static void main(String[] args) {
        String ruta="docs/configuracion.txt";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                String asd = line.split(":")[1];
                String valorS = line.split(":")[1].substring(0, asd.length() - 1);
                int valor = Integer.parseInt(valorS);
                if (line.contains("Tam")) {
                    tamanoBuffer = valor;
                } else if (line.contains("Cli")) {
                    numeroClientes = valor;
                } else if (line.contains("Serv")) {
                    numeroServidores = valor;
                } else {
                    numeroMensajes = valor;
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        Buffer buff= new Buffer(tamanoBuffer);
        Cliente clientes[]= new Cliente[numeroClientes];
        for(int i=0; i<numeroClientes; i++){
            clientes[i] = new Cliente(numeroMensajes, buff);
            System.out.println("cliente creado");
            clientes[i].start();
            System.out.println("cliente start");

        }
        Servidor servidores[]= new Servidor[numeroServidores];
        for(int i=0; i<numeroServidores; i++){
            servidores[i] = new Servidor(buff);
            System.out.println("servidor creado");
            servidores[i].start();
            System.out.println("servidor start");
        }
        //try {
        //    Thread.sleep(200000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        for(int i=0; i<numeroClientes; i++){
            try {
                clientes[i].join();
                System.out.println("cliente ha terminado");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
