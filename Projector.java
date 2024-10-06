import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class Projector {
    String projectorName;
    String modelNumber;
    String department;
    String classAndSection;
    String resolution;
    String dateOfPurchase;
    public Projector(String projectorName, String modelNumber, String department,
    String classAndSection,
    String resolution, String dateOfPurchase) {
        this.projectorName = projectorName;
        this.modelNumber = modelNumber;
        this.department = department;
        this.classAndSection = classAndSection;
        this.resolution = resolution;
        this.dateOfPurchase = dateOfPurchase;
    }
}
public class Main {
    private static final String PROJECTOR_CSV_FILE_NAME =
    "projector_data.csv";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Projector> projectors =
        loadFromFile(PROJECTOR_CSV_FILE_NAME, Projector.class);
        System.out.println(" PROJECTOR MANAGEMENT
        SYSTEM ");
    while (true) {
    System.out.println(" ");
    System.out.println("CONTENTS ARE >>");
    System.out.println("1. Add Projector");
    System.out.println("2. Search for Projector");
    System.out.println("3. Display Projectors");
    System.out.println("4. Save to File");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    switch (choice) {
        case 1:
        addProjector(scanner, projectors);
        saveToFile(projectors, PROJECTOR_CSV_FILE_NAME);
        break;
        case 2:
        searchProjector(scanner, projectors);
        break;
        case 3:
        displayProjectors(projectors);
        break;
        case 4:
        saveToFile(projectors, PROJECTOR_CSV_FILE_NAME);
        System.out.println("Projector data saved to file.");
        break;
        case 5:
        System.out.println("Exiting program. Thank you!");
        System.exit(0);
        default:
        System.out.println("Invalid choice. Please enter a valid option.");
    }
    }
    }
    private static void addProjector(Scanner scanner, ArrayList<Projector>
    projectors) {
        System.out.print("Enter projector name: ");
        String projectorName = scanner.next();
        System.out.print("Enter model number: ");
        String modelNumber = scanner.next();
        System.out.print("Enter department: ");
        String department = scanner.next();
        System.out.print("Enter section: ");
        String classAndSection = scanner.next();
        System.out.print("Enter resolution: ");
        String resolution = scanner.next();
        System.out.print("Enter date of purchase: ");
        String dateOfPurchase = scanner.next();
        Projector projector = new Projector(projectorName, modelNumber,
        department, classAndSection, resolution, dateOfPurchase);
        projectors.add(projector);
        System.out.println("Projector added successfully!");
    }
    private static void searchProjector(Scanner scanner, ArrayList<Projector>
    projectors) {
        System.out.print("Enter department: ");
        String inputDepartment = scanner.next();
        System.out.print("Enter section: ");
        String inputClassAndSection = scanner.next();
        boolean found = false;
        for (Projector projector : projectors) {
            if (projector.department.equalsIgnoreCase(inputDepartment) &&
            projector.classAndSection.equalsIgnoreCase(inputClassAndSection))
                {
                System.out.println("Projector Details:");
                System.out.println("Projector Name: " + projector.projectorName);
                System.out.println("Model Number: " + projector.modelNumber);
                System.out.println("Department: " + projector.department);
                System.out.println("Section: " + projector.classAndSection);
                System.out.println("Resolution: " + projector.resolution);
                System.out.println("Date of Purchase: " + projector.dateOfPurchase);
                found = true;
                break;
                }
        }
        if (!found) {
            System.out.println("No projector found for the given department and
            class/section.");
        }
        }
        private static void displayProjectors(ArrayList<Projector> projectors) {
        if (projectors.isEmpty()) {
            System.out.println("No projectors to display.");
        } else {
            for (Projector projector : projectors) {
                System.out.println("Projector Name: " + projector.projectorName);
                System.out.println("Model Number: " + projector.modelNumber);
                System.out.println("Department: " + projector.department);
                System.out.println("Section: " + projector.classAndSection);
                System.out.println("Resolution: " + projector.resolution);
                System.out.println("Date of Purchase: " + projector.dateOfPurchase);
                System.out.println();
            }
        }
        }
        private static <T> ArrayList<T> loadFromFile(String fileName, Class<T> clazz) {
            ArrayList<T> data = new ArrayList<>();
            try (Scanner fileScanner = new Scanner(new File(fileName))) {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine(); // Skip header line
                while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] values = line.split(" , ");
            if (clazz == Projector.class) {
                data.add((T) new Projector(values[0], values[1], values[2],
                values[3], values[4], values[5]));
            }
            }
            }
            } 
            catch (FileNotFoundException e) {
            System. out .println("File not found: " + fileName);
            }
            return data;
        }
        private static <T> void saveToFile(ArrayList<T> data, String fileName) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write( getHeader (data.get(0)));
            for (T item : data) {
                writer.write( getFormattedData (item));
            }
            System. out .println("Data saved to file: " + fileName);
            } catch (IOException e) {
            System. out .println("Error saving data to file: " + e.getMessage());
            }
            }
            private static <T> String getHeader(T item) {
                StringBuilder header = new StringBuilder();
                for (java.lang.reflect.Field field : item.getClass().getDeclaredFields()) {
                header.append(field.getName()).append(",");
                }
                header.deleteCharAt(header.length() - 1); // Remove trailing comma
                header.append("\n");
                return header.toString();
        }
        private static <T> String getFormattedData(T item) {
            StringBuilder data = new StringBuilder();
            for (java.lang.reflect.Field field : item.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                data.append(field.get(item)).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            }
            data.deleteCharAt(data.length() - 1); // Remove trailing comma
            data.append("\n");
            return data.toString();
            }
        }