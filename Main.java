public class Main {
    private static final CustomerDB db = new CustomerDB("insurance.csv");

    public static void main(String[] args) {
        while (true) {
            int requestId = Menu.getAnswer();
            if (requestId < 0) break;
            else executeRequest(requestId); // Customer.printHeader();
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    public static void executeRequest(int id) {
        switch (id) {
            case 11:
                db.fetch("male", 1, false);
                break;
            case 111:
                db.fetch("male", "southwest", false);
                break;
            case 112:
                db.fetch("male", "southeast", false);
                break;
            case 12:
                db.fetch("female", 1, false);
                break;
            case 121:
                db.fetch("female", "southwest", false);
                break;
            case 122:
                db.fetch("female", "southeast", false);
                break;
            case 13:
                db.fetch("southwest", 5, false);
                break;
            case 14:
                db.fetch("southeast", 5, false);
                break;
            case 21:
                db.fetch("male", 1, true);
                AutomatedReport.describe("male customers");
                break;
            case 211:
                db.fetch("male", "southwest", true);
                AutomatedReport.describe("male customers in the southwest");
                break;
            case 212:
                db.fetch("male", "southeast", true);
                AutomatedReport.describe("male customers in the southeast");
                break;
            case 22:
                db.fetch("female", 1, true);
                AutomatedReport.describe("female customers");
                break;
            case 221:
                db.fetch("female", "southwest", true);
                AutomatedReport.describe("female customers in the southwest");
                break;
            case 222:
                db.fetch("female", "southeast", true);
                AutomatedReport.describe("female customers in the southeast");
                break;
            case 23:
                db.fetch("southwest", 5, true);
                AutomatedReport.describe("customers in the southwest");
                break;
            case 24:
                db.fetch("southeast", 5, true);
                AutomatedReport.describe("customers in the southeast");
                break;
            case 3:
                // System.out.println("You have chosen 3 - Search a customer");
                System.out.print("Enter customer ID: ");
                Customer c = db.getRecordById(EasyScanner.nextInt());
                if (c != null) {
                    System.out.println("(index=" + (c.id - 1) + ", " + c + ")");
                } else {
                    System.out.println("Not found");
                }
                break;
            case 4:
                // System.out.println("You have chosen 4 - Delete a customer");
                System.out.print("Enter customer ID: ");
                c = db.getRecordById(EasyScanner.nextInt());
                if (c != null) {
                    System.out.println("This customer will be deleted.");
                    System.out.println("(index=" + (c.id - 1) + ", " + c + ")");
                    c.id = -1;
                } else {
                    System.out.println("Not found");
                }
                break;
            case 5:
                int customerId = db.add(Menu.getCustomerInfos());
                if (customerId > 0) {
                    System.out.println("New customer successfully created (ID:" + customerId + ")");
                }
                break;
            case 1:
            case 6:
                db.fetch();
                break;
            case 61:
                db.sortByAge();
                break;
            case 62:
                db.sortByChildren();
                break;
            case 63:
                db.sortBySex();
                break;
            case 64:
                db.sortByRegion();
                break;
            case 65:
                db.sortByBmi();
                break;
            case 66:
                db.sortByCharges();
                break;
        }
    }
}