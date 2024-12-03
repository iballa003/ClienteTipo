
package clientetipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTipo {

    public static void main(String[] args) {
        //Ip y puerto del servidor.
        String host = "localhost";
        int port = 12345;
        // Variables
        int limiteInferior = 1;
        int limiteSuperior = 100;
        boolean adivinado = false;
        
        try {
            //COnectarse al servidor
            Socket socket = new Socket(host,port);
            System.out.println("Conectado al servidor "+host+" con el puerto "+port+".");
            
            //Enviar mensaje
            int numeroMensaje = 0;
            while(adivinado == false){
                 
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("Limite inferior: "+limiteInferior+" Limite superior: "+limiteSuperior);
                int answerClient = numeroAleatorio(limiteInferior,limiteSuperior);
                System.out.println("Número elegido por el cliente: "+answerClient);
                output.println(answerClient);

                String answer = input.readLine();
                System.out.println("Servidor: "+answer);
                if("¡Correcto!".equals(answer)){
                    adivinado = true;
                    
                }
                if ("Es menor".equals(answer)) {                
                    limiteSuperior = answerClient-1;
                    
                }
                if("Es mayor".equals(answer)){
                    limiteInferior = answerClient+1;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static int numeroAleatorio(int valorMinimo, int valorMaximo){
        Random r = new Random();
        return valorMinimo + r.nextInt(valorMaximo - valorMinimo +1);
    }
}
