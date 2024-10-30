public class Customer {
    private String repr = "id=%d, age=%d, sex=%s, bmi=%f, children=%d, smoker=%s, region=%s, charges=%f";
    /// private String repr = "%10d %5d %10s %6.4f %10d %10b %10s %6.4f";
    public double age, bmi, children, charges;
    public String sex, region;
    public boolean smoker;
    public int id;
    Customer(int id, double age, String sex, double bmi, double children, boolean smoker, String region, double charges) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.bmi = bmi;
        this.children = children;
        this.smoker = smoker;
        this.region = region;
        this.charges = charges;
    }

    public String toString() {
        return String.format(repr, id, (int)age, sex, bmi, (int)children, smoker, region, charges);
    }

    public static void printHeader() {
        Customer.printDivider();
        System.out.printf("%10s %5s %10s %10s %10s %10s %10s %10s\n", "CustomerID", "Age", "Gender", "Bmi", "Children", "Smoker", "Region", "Charges");
        Customer.printDivider();
    }

    public static void printDivider() {
        // System.out.println("+-----+----+------+------------+--------+-----------+-----------+------------+------------");
        System.out.println("+-------+----+------+------------+--------+-----------+-----------+------------+--------");
    }
}
/*
CustomerID   Age     Gender        Bmi   Children     Smoker Region        Charges
----------   -----   ----------    ---
 */