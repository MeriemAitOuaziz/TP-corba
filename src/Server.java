
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;

import java.io.FileWriter;
import java.io.IOException;

public class Server {
    public static void main(String args[]) {
        java.util.Properties props = System.getProperties();

        int status = 0;
        org.omg.CORBA.ORB orb = null;

        try {
            orb = org.omg.CORBA.ORB.init(args, props);
            status = run(orb);
        } catch (Exception ex) {
            ex.printStackTrace();
            status = 1;
        }

        if (orb != null) {
            try {
                orb.destroy();
            } catch (Exception ex) {
                ex.printStackTrace();
                status = 1;
            }
        }
        System.exit(status);
    }

    static int run(org.omg.CORBA.ORB orb)
            throws org.omg.CORBA.UserException, IOException {
        try {
            org.omg.PortableServer.POA rootPOA =
                    org.omg.PortableServer.POAHelper.narrow(
                            orb.resolve_initial_references("RootPOA"));

            org.omg.PortableServer.POAManager manager =
                    rootPOA.the_POAManager();

            //objet d impl
            HelloImpl hello = new HelloImpl();
            //creation de la ref corba de l'objet
            Hello aHello = hello._this(orb);
            //ecrire ref sur fichier
            String ref = orb.object_to_string(aHello);
            FileWriter out = new FileWriter("hello.ref");
            out.write(ref);
            out.close();

            manager.activate();
            orb.run();

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return 1;
        }
        return 0;
    }}
