package cmd;

public class Main {
    public static void main(String[] args) {
        CostConverterCommandLineApp costConverterApp = new CostConverterCommandLineApp();
        long cost = costConverterApp.run(args);
        System.out.println(cost);
    }
}
