import java.util.ArrayList;

public class AutomatedReport {
    private static ArrayList<Double> ageList = new ArrayList<>();
    private static ArrayList<Double> bmiList = new ArrayList<>();
    private static ArrayList<Double> childrenList = new ArrayList<>();
    private static ArrayList<Double> chargesList = new ArrayList<>();

    public static void reset() {
        ageList = new ArrayList<>();
        bmiList = new ArrayList<>();
        childrenList = new ArrayList<>();
        chargesList = new ArrayList<>();
    }

    public static void addRecord(double age, double bmi, double children, double charges) {
        ageList.add(age);
        bmiList.add(bmi);
        childrenList.add(children);
        chargesList.add(charges);
    }

    // public static void reset()

    public static void describe(String label) {
        int count = ageList.size();
        if (count > 0) {
            System.out.printf("There are %d %s.\n", ageList.size(), label);
            System.out.printf("The average age is %f. The maximum age is %d. The minimum age is %d.\n", avg(ageList), (int) max(ageList), (int) min(ageList));
            System.out.printf("The average bmi is %f. The maximum bmi is %f. The minimum bmi is %f.\n", avg(bmiList), max(bmiList), min(bmiList));
            System.out.printf("The average number of children is %f. The maximum number of children is %d. The minimum number of children is %d.\n", avg(childrenList), (int) max(childrenList), (int) min(childrenList));
            System.out.printf("The average amount of charges is %f. The maximum amount of charges is %f. The minimum amount of charges is %f.\n", avg(chargesList), max(chargesList), min(chargesList));
        } else {
            System.out.println("No " + label);
        }
        AutomatedReport.reset();
    }

    private static double avg(ArrayList<Double> arr) {
        double total = 0;
        for (double num:arr) {
            total += num;
        }
        return total / arr.size();
    }

    private static double min(ArrayList<Double> arr) {
        double min = arr.get(0);
        for (double num:arr) {
            if (num < min) min = num;
        }
        return min;
    }

    private static double max(ArrayList<Double> arr) {
        double max = arr.get(0);
        for (double num:arr) {
            if (max < num) max = num;
        }
        return max;
    }

    public static ArrayList<Double> getAgeList() {
        return ageList;
    }

    public static ArrayList<Double> getBmiList() {
        return bmiList;
    }

    public static ArrayList<Double> getChildrenList() {
        return childrenList;
    }

    public static ArrayList<Double> getChargesList() {
        return chargesList;
    }
}
