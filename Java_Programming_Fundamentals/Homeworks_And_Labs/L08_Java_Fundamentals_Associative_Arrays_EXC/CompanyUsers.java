package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        Map<String, List> companiesMap = new LinkedHashMap<>();

        while (!command.equalsIgnoreCase("end")) {
            String[] commandParts = command.split(" -> ");
            String company = commandParts[0];
            String employeeId = commandParts[1];

            if (!companiesMap.containsKey(company)) {
                List<String> employeeIdList = new ArrayList<>();
                companiesMap.put(company, employeeIdList);
            }

            List<String> currentEmployees = companiesMap.get(company);

            if(!currentEmployees.contains(employeeId)) {
                currentEmployees.add(employeeId);
            }


            command = scanner.nextLine();

        }

        for (Map.Entry<String, List> entry : companiesMap.entrySet()) {
            String companyName = entry.getKey();
            List<String> employees = entry.getValue();

            System.out.printf("%s%n", companyName);

            for (String employee : employees) {
                System.out.printf("-- %s%n", employee);
            }
        }

    }

}
