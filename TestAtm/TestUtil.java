package TestAtm;

public class TestUtil {
    public static void test(String testname, Boolean condition, String message) throws Exception {
        if(condition == false) {
            throw new Exception(testname + " Failed: " + message);
        }
        else {
            System.out.println(testname + " Passed");
        }
    }
}
