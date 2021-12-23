package by.stepanovich.xmlparsers.validator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class BaseValidatorMainTest {

    @Test
    public void isFileValid() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        File fileXML = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\tariffs.xml");
        File fileXSD = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\tariffs.xsd");
        Assert.assertTrue(baseValidatorMain.isFileValid(fileXML, fileXSD));
    }

    @Test
    public void isNotFileValid() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        File fileXML = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\not_valid_file_tariffs.xml");
        File fileXSD = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\tariffs.xsd");
        Assert.assertFalse(baseValidatorMain.isFileValid(fileXML, fileXSD));
    }

    @Test
    public void isNullFileValid() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        Assert.assertFalse(baseValidatorMain.isFileValid(null, null));
    }

    @Test
    public void isNullFile() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        File fileXSD = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\tariffs.xsd");
        Assert.assertFalse(baseValidatorMain.isFileValid(null, fileXSD));
    }

    @Test
    public void isNullXSD() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        File fileXML = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\not_valid_file_tariffs.xml");
        Assert.assertFalse(baseValidatorMain.isFileValid(fileXML, null));
    }

    @Test
    public void ReplacedFiles() {
        BaseValidatorMain baseValidatorMain = new BaseValidatorMain();
        File fileXML = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\not_valid_file_tariffs.xml");
        File fileXSD = new File("C:\\Users\\namea\\IdeaProjects\\parser\\src\\main\\resources\\tariffs.xsd");
        Assert.assertFalse(baseValidatorMain.isFileValid(fileXSD, fileXML));
    }
}