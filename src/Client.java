import org.omg.CORBA.ORB;

import java.io.*;

public class Client {
    public static void main(String args[]) {
        try {
            org.omg.CORBA.ORB orb = null;
            // initialisation de l ’ORB
            orb = ORB.init(args, null);

            //connect to remote object
            String refFile = "hello.ref";
            FileInputStream file = new FileInputStream(refFile);
            BufferedReader in = new BufferedReader(new InputStreamReader(file));
            String ref = in.readLine();
            file.close();

            org.omg.CORBA.Object obj = orb.string_to_object(ref);
            Hello hello = HelloHelper.narrow(obj);
            hello.hello();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
