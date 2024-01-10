public class Assignment {
    public static void main(String[] args) {
        String str= "Srikar";
        int n=10;
        double dubl=85.43;

        total(str,n,dubl);
    }
    static void total(String s,int num,double d ) {
        double v = ((double) num + d);
        System.out.println(s + v);
    }
}
