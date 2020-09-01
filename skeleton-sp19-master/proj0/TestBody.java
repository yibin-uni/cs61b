public class TestBody {

    public static void main(String[] args) {
        Body b1 = new Body(3,2.8,0,0,3.7e6,"jupiter.gif");
        Body b2 = new Body(6,6.8,2.7,0.9,5.47e6,"jupiter.gif");
        System.out.println(b1.calcForceExertedBy(b2));
    }
}