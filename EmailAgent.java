
import java.io.*;
import java.net.*;
import java.util.*;

public class EmailAgent
{
    public static void main(String[] args) throws Exception
    {

Socket socket = new Socket ("smtp.gmail.com", 987);



        // Create a BufferedReader to read a line at a time.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // Read greeting from the server.
        String response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("220")) {
            throw new Exception("220 reply not received from server.");
        }

        // Get a reference to the socket's output stream
        OutputStream os = socket.getOutputStream();

        // Send HELO command and get server response.
        String command = "HELO TEAM\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send MAIL FROM command.
        command = "MAIL FROM: reitheshiba@gmail.com\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send RCPT TO command.
        command = "RCPT TO: reitheshiba@gmail.com\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send DATA command.
        command = "DATA\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("354")) {
            throw new Exception("354 reply not received from server.");
        }

        // Send message data.
        os.write("SUBJECT: testing TEST MESSAGE\r\n\r\n".getBytes("US-ASCII"));
        os.write("HOLA HOLA ,\r\n".getBytes("US-ASCII"));
        os.write("\r\n".getBytes("US-ASCII"));
        os.write("What a thinker.\r\n".getBytes("US-ASCII"));

        // End with line with a single period.
        os.write(".\r\n".getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send QUIT command.
        command = "QUIT\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
    }
}

