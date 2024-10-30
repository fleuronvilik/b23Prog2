import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Integer> indexes = new ArrayList<>();
    private final ArrayList<Double> age, children, bmi, charges;
    private int length, skip;
    private final String repr = "(index=%d, id=%d, age=%d, sex=%s, bmi=%f, children=%d, smoker=%s, region=%s, charges=%s)\n";

    CustomerDB(String pathname) {
        int i = 0;
        Scanner scanner = EasyScanner.fopen(pathname);
        scanner.nextLine(); // skipHeader
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();

            String[] values = row.split(",");
            double age = Double.parseDouble(values[0]), bmi = Double.parseDouble(values[2]), children = Double.parseDouble(values[3]), charges = Double.parseDouble(values[6]);
            boolean smoker = values[4].equals("yes") ? true : false;
            AutomatedReport.addRecord(
                    age, //Double.parseDouble(values[0]),
                    bmi, //Double.parseDouble(values[2]),
                    children, //Double.parseDouble(values[3]),
                    charges // Double.parseDouble(values[6])
            );

            customers.add(new Customer(i+1, age, values[1], bmi, children, smoker, values[5], charges));
            indexes.add(i);
            i++;
        }
        this.age = AutomatedReport.getAgeList();
        this.children = AutomatedReport.getChildrenList();
        this.bmi = AutomatedReport.getBmiList();
        this.charges = AutomatedReport.getChargesList();
        AutomatedReport.reset();

        length = i;
        scanner.close();
    }

    private ArrayList<Integer> sort(ArrayList<Double> values) {
        ArrayList<Double> copyOfValues = new ArrayList<>(values);
        ArrayList<Integer> copyOfIndexes = new ArrayList<>(indexes);

        for (int i = 0, j = length - 1; i < j; i++) {
            for (int k = 0; k < j; k++) {
                double currVal = copyOfValues.get(k), nextVal = copyOfValues.get(k+1);
                int currIndex = copyOfIndexes.get(k), nextIndex = copyOfIndexes.get(k+1);
                if (nextVal < currVal) {
                    // int tmp = currIndex;
                    copyOfIndexes.set(k, nextIndex);
                    copyOfIndexes.set(k+1, currIndex); //indexes.set(k+1, tmp);

                    copyOfValues.set(k, nextVal);
                    copyOfValues.set(k+1, currVal);
                }
            }
        }
        return copyOfIndexes;
    }

    private void sort(String col) {
        ArrayList<Integer> sortedIndexesList = null;
        switch(col) {
            case "age": // age
                sortedIndexesList = this.sort(age);
                break;
            case "bmi":
                sortedIndexesList = this.sort(bmi);
                break;
            case "children":
                sortedIndexesList = this.sort(children);
                break;
            case "charges":
                sortedIndexesList = this.sort(charges);
                break;
        }

        // Customer.printHeader();
        int j = 0;
        for (int i : sortedIndexesList) {
            Customer c = this.getRecordById(i+1);
            if (c != null) {
                this.printCustomerWith(j, c);
                j++;
            }
        }
        // Customer.printDivider();
    }

    public void sortByAge() {
        sort("age");
    }

    public void sortByBmi() {
        sort("bmi");
    }

    public void sortBySex() {
        skip = 0;
        skip = this.fetch("female", 1, false);
        skip += this.fetch("male", 1, false);
    }

    public void sortByChildren() {
        sort("children");
    }

    public void sortByRegion() {
        skip = 0;
        skip = this.fetch("northeast", 5, false);
        skip += this.fetch("northwest", 5, false);
        skip += this.fetch("southeast", 5, false);
        skip += this.fetch("southwest", 5, false);
    }

    public void sortByCharges() {
        sort("charges");
    }

    public Customer getRecordById(int id) { // TODO: rename to getCustomerById
        Customer c = id <= length ? customers.get(id - 1) : null;
        //if (c.id > 0) return c;
        return c.id > 0 ? c : null;
    }

    public void fetch() {
        int j = 0;
        for (int i = 1; i <= length; i++) {
            Customer c = this.getRecordById(i); // customers.get(i-1);
            if (c != null) {
                this.printCustomerWith(j, c);
                j++;
            }
        }
        //System.out.println("Count: " + j);
    }

    public int fetch(String gender, String region, boolean isReport) {
        int j = 0; skip = 0;
        for (int i = 0; i < length; i++) {
            Customer c = customers.get(i);
            if (c.id > 0 && c.sex.equals(gender) && c.region.equals(region)) {
                if (isReport) {
                    AutomatedReport.addRecord(
                            c.age,
                            c.bmi,
                            c.children,
                            c.charges
                    );
                } else {
                    this.printCustomerWith(j+skip, c); j++;// System.out.println("(index=" + i + ", " + c + ")");
                    // System.out.printf("index=%-4d|", i);
                    // System.out.println(c);
                }
            }
        }
        return j;
    } // TODO: Durchnummerieren mit j in fetch & sort

    public int fetch(String value, int colIndex, boolean isReport) {
        int j = 0; skip = 0;
        for (int i = 0; i < length; i++) {
            Customer c = customers.get(i);
            String colValue = "";
            switch(colIndex) {
                case 1:
                    colValue = c.sex;
                    break;
                case 5:
                    colValue = c.region;
                    break;
            }
            if (c.id > 0 && colValue.equals(value)) {
                if (isReport) {
                    AutomatedReport.addRecord(
                            c.age,
                            c.bmi,
                            c.children,
                            c.charges
                    );
                } else {
                    this.printCustomerWith(j+skip, c); j++; // System.out.println("(index=" + i + ", " + c + ")");
                    // System.out.printf("index=%-4d|", i);
                    // System.out.println(c);
                }
            }
        }
        return j;
    }

    public int add(Customer c) {
        if (c != null) {
            c.id = length + 1;
            customers.add(c);
            indexes.add(length); // c.id -1
            age.add(c.age);
            bmi.add(c.bmi);
            children.add(c.children);
            charges.add(c.charges);
            length = c.id;
            return c.id;
        }
        return -1;
    }

    private void printCustomerWith(int index, Customer c) {
        System.out.println("(index=" + index + ", " + c + ")");
        // System.out.printf("| %5d |", index);
        // System.out.println(c);
    }
}
