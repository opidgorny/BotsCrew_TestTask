package com.opidgorny.bot;

import com.opidgorny.bot.service.impl.DepartmentServiceImpl;
import com.opidgorny.bot.service.impl.LectorServiceImpl;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleBot {

    private Pattern pattern;

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
    private LectorServiceImpl lectorService = new LectorServiceImpl();

    //Used to define what answer should be produced
    final Map<String, String> KEY_SENTENCES = new HashMap<String, String>() {{
        put("who is head of department", "department" );
        put("Show\\s.*statistic", "statistics");
        put("Show the average salary for department", "salary");
        put("Show count of employee for", "employees");
        put("Global search by", "globalSearch");
    }};

    SimpleBot(){}

    //method for testing code pieces
//    public String sayInReturnTest(String msg, boolean ai) {
//            String message =
//                    String.join(" ", msg.split("[ {,|.}?]+"));
//            System.out.println(message);
//
//            return lectorService.getLectorByFirstNameContainingOrLastNameContaining(getLastWord(message)) + "\n"
//                    + departmentService.getDepartmentNameByNameContaining(getLastWord(message));
//    }

    public String sayInReturn(String msg) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));

            //Needs to be refactored. Change if clauses with switch using enums, probably
        System.out.println(message.split(" ").length);
            if(message.split(" ").length >= 3) {

                if (message.substring(0, message.lastIndexOf(" ")).equals(getKeysFromValue(KEY_SENTENCES, "department"))) {

                    return departmentKey(message, KEY_SENTENCES);
                } else if (message.equals(("Show " + message.substring(5, message.lastIndexOf(" ")) + " statistic").toLowerCase())) {

                    return statisticKey(message, KEY_SENTENCES);
                } else if (message.substring(0, message.lastIndexOf(" ")).equals(getKeysFromValue(KEY_SENTENCES, "salary"))) {

                    return salaryKey(message, KEY_SENTENCES);
                } else if (message.substring(0, message.lastIndexOf(" ")).equals(getKeysFromValue(KEY_SENTENCES, "employees"))) {

                    return employeesKey(message, KEY_SENTENCES);
                } else if (message.substring(0, message.lastIndexOf(" ")).equals(getKeysFromValue(KEY_SENTENCES, "globalSearch"))) {

                    return globalSearchKey(message, KEY_SENTENCES);
                }
            }

            return "You are using wrong commands";
    }

    //Returns head of department by {department_name}
    private String departmentKey(String message, Map<String, String> map) {

        String departmentName = getLastWord(message);
        pattern = Pattern.compile(getKeysFromValue(map, "department") + " " + departmentName);
        if (pattern.matcher(message).find()) {
            return "Head of " + departmentName + " department is " + departmentService.getDepartmentHead(departmentName);
        }
        return null;
    }

    //Returns statistics on based on {department_name}
    private String statisticKey(String message, Map<String, String> map) {

        String departmentName = message.substring(5, message.lastIndexOf(" "));
        pattern = Pattern.compile("show " + departmentName + " statistic");

        if (pattern.matcher(message).find()) {
            return departmentService.getDepartmentEmployees(departmentName);
        }
        return null;
    }

    //Returns average department salary by {department_name}
    private String salaryKey(String message, Map<String, String> map) {

        String departmentName = getLastWord(message);
        pattern = Pattern.compile(getKeysFromValue(map, "salary") + " " + departmentName);
        if (pattern.matcher(message).find()) {
            return "The average salary of " + departmentName + " is: " + departmentService.getDepartmentAverageSalary(departmentName);
        }
        return null;
    }

    //Returns count of employee for {department_name}
    private String employeesKey(String message, Map<String, String> map) {

        String departmentName = getLastWord(message);
        pattern = Pattern.compile(getKeysFromValue(map, "employees") + " " + departmentName);
        if (pattern.matcher(message).find()) {
            return "Total count of employees: " + departmentService.getDepartmentEmployeeCount(departmentName);
        }
        return null;
    }

    //Returns global search by {template}
    private String globalSearchKey(String message, Map<String, String> map) {

        String template = getLastWord(message);
        pattern = Pattern.compile(getKeysFromValue(map, "globalSearch") + " " + template);
        if (pattern.matcher(message).find()) {
            //Using another method that loops through each db table
            return globalSearch(template);
        }
        return null;
    }

    private String globalSearch(String template) {
        //Global Search by Departments and Lectors
        String globalDepartments = departmentService.getDepartmentNameByNameContaining(template) + "\n";
        String globalLectors = lectorService.getLectorByFirstNameContainingOrLastNameContaining(template);

        return globalDepartments + globalLectors;

    }

    private String getKeysFromValue(Map<?, ?> map, Object value) {
        List<Object> list = new ArrayList<>();
        for(Object o : map.keySet()) {
            if(map.get(o).equals(value)) {
                list.add(o);
            }
        }
        String listStr = list.stream().map(Object::toString)
                                .collect(Collectors.joining(" ")).toLowerCase();
        return listStr;
    }

    private String getLastWord(String text) {
        int index = text.lastIndexOf(" ");
        String lastWord = String.join(" ", text.substring(index + 1).split("[{,|!.}?]+"));
        return lastWord;
    }
}
