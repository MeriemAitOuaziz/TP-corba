import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

public class HelloImpl extends HelloPOA {
    @Override
    public void hello() {
        System.out.println("hello client");
    }
}
