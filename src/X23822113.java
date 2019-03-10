public class X23822113 {
    public static void main(String args[]) throws Exception{
        String a = "X^2+2X^3+3X^2+4X^4";
        String b = "2X^2+4X";
		Polynomial p = new Polynomial(a), q = new Polynomial(b);
		Utility.run(p, q);
    }
}