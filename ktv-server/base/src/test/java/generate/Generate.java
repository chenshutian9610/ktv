package generate;

import org.tree.commons.generate.generator.TableGenerator;

public class Generate {
    public static void main(String[] args) throws Exception {
        TableGenerator generator = new TableGenerator(Generate.class.getPackage().getName());
        generator.setConfigProperties("conf.properties");
        generator.forward();    //  正向工程
        generator.reverse();    //  逆向工程
    }
}