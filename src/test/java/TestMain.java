import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestMain {


    @Test
    public void testMain() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File(".gitignore"));
        System.setIn(fips);
        Main.main(args);
        System.setIn(original);
    }

}
