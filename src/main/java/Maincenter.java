import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import Action.CreateData;
import model.Student;
import model.Subject;

class HashTable {

  
    static class Node {
        String id;
        String value;
        Node next;

        Node(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    private Node[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        table = new Node[size];
    }

  
    public void insert(int index,  String id, String value) {

        if (id == null || id.isEmpty()) return;

        char first = Character.toUpperCase(id.charAt(0));

        if (first < 'A' || first > 'Z') return;

       
        Node current = table[index];

        
        while (current != null) {
            if (current.id.equalsIgnoreCase(id)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        
        Node newNode = new Node(id, value);
        newNode.next = table[index];
        table[index] = newNode;
    }

 public List<String> search(String search) {

    List<String> results = new ArrayList();

    if (search == null || search.isEmpty())
        return results;

    search = search.toLowerCase();

    char first = Character.toUpperCase(search.charAt(0));

    if (first < 'A' || first > 'Z')
        return results;

    int index = first - 'A';

    Node current = table[index];

    while (current != null) {

        if ((current.value.toLowerCase().contains(search)
                || current.id.equalsIgnoreCase(search))) {

            results.add(current.id);
        }

        current = current.next;   
    }
    
    return results;
}


    public void printTable() {
        for (int i = 0; i < size; i++) {

            Node current = table[i];

            if (current != null) {
                System.out.print((char) ('A' + i) + " : ");

                while (current != null) {
                    System.out.print("[" + current.id + " - " + current.value + "] ");
                    current = current.next;
                }

                System.out.println();
            }
        }
    }
}

public class Maincenter {

    public static void Menu() {
        String[] menu = {
                "------------------------------------------Quan ly hoc vien-----------------------------------------",
                " 1. Danh sach hoc vien",
                " 2. Quan ly lop hoc",
                " 3. Tim kiem hoc vien",
                " 4. Quan ly khoa hoc",
                " 5. Quan ly diem thi",
                " 6. Thoat chuong trinh",
                "___________________________________________________________________________________________________",
        };

        for (String line : menu) {
            System.out.printf("|%-99s|%n", line);
        }
    }
    
   public static List<Student> dataByid(List<String> ids, List<Student> students) {

    // Trim toàn bộ ids trước
    Set<String> idSet = ids.stream()
            .map(String::trim)
            .collect(Collectors.toSet());

    List<Student> results = new ArrayList<>();

    for (Student s : students) {
        if (idSet.contains(s.getId().trim())) {
            results.add(s);
        }
    }

    return results;
}

    public static HashTable buildHashTable(List<Student> students) {
        HashTable hashTable = new HashTable(26); 
        for (Student s : students) { 
            String name = s.getName();
            if (name != null && !name.isEmpty()) { 
                char first = Character.toUpperCase(name.charAt(0)); 
                if (first >= 'A' && first <= 'Z') { 
                    int index = first - 'A'; 
                    hashTable.insert(index, s.getId(), s.getName());
                    }
                } 
        }
        return hashTable;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String filePath = "Student_200_No_Accent.xlsx";
        List<Student> students = CreateData.readSheet(filePath);


        HashTable hashTable = buildHashTable(students);

        int choice;

        while (true) {

            Menu();
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:

                    String format = "| %-6s | %-20s | %-6s | %-40s | %-15s | %-25s |%n";
                    String line = "-----------------------------------------------------------------------------------------------------------------------------------";

                    System.out.println(line);
                    System.out.printf(format, "ID", "Name", "Year", "Address", "Phone", "Email");
                    System.out.println(line);

                    for (Student s : students) {
                        System.out.printf(format,
                                s.getId(),
                                s.getName(),
                                s.getYear(),
                                s.getAddress(),
                                s.getPhone(),
                                s.getEmail()
                        );
                    }

                    System.out.println(line);
                    break;

                case 2:

                    String lines = "+--------+----------------------+------------+";

                    System.out.println(lines);
                    System.out.printf("| %-6s | %-20s | %-8s |%n", "ID", "Mon hoc", "So tin chi");
                    System.out.println(lines);

                    Subject.values().forEach(s ->
                            System.out.printf("| %-6s | %-20s | %-10d |%n",
                                    s.getId(), s.getName(), s.getCredits())
                    );

                    System.out.println(lines);
                    break;

                case 3:
                
                    System.out.print("Tim kiem : ");
                    String search = sc.nextLine();

                    List<String> results = hashTable.search(search);

                    if (!results.isEmpty()) {
                        System.out.println("Tim thay : " + results.size() + " ket qua.");
                        List<Student> foundStudents = dataByid(results, students);
                        for (Student s : foundStudents) {
                            System.out.printf("| %-6s | %-20s | %-6s | %-40s | %-15s | %-25s |%n",
                                    s.getId(),
                                    s.getName(),
                                    s.getYear(),
                                    s.getAddress(),
                                    s.getPhone(),
                                    s.getEmail()
                            );
                        }
                    } else {
                        System.out.println("Khong tim thay hoc vien!");
                    }
                 
                    for (Student s : students) {
                        System.out.printf("| %-6s | %-20s | %-6s | %-40s | %-15s | %-25s |%n",
                                s.getId(),
                                s.getName(),
                                s.getYear(),
                                s.getAddress(),
                                s.getPhone(),
                                s.getEmail()
                        );
                    }


                    break;

                case 4:
                    System.out.println("Quan ly khoa hoc");
                    break;

                case 5:
                    System.out.println("Quan ly diem thi");
                    break;

                case 6:
                    System.out.println("Thoat chuong trinh");
                    sc.close();
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
