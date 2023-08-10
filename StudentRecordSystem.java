import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    String name;
    int age;
    String grade;

    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}

public class StudentRecordSystem extends JFrame {
    private ArrayList<Student> studentList = new ArrayList<>();
    private JTextArea textArea;

    public StudentRecordSystem() {
        setTitle("Student Record System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton deleteButton = new JButton("Delete Student");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudents();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);

        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        Container container = getContentPane();
        container.add(panel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog("Enter student name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter student age:"));
        String grade = JOptionPane.showInputDialog("Enter student grade:");

        Student student = new Student(name, age, grade);
        studentList.add(student);
        textArea.append("Student added: " + student.name + "\n");
    }

    private void viewStudents() {
        textArea.setText("");
        for (Student student : studentList) {
            textArea.append("Name: " + student.name + ", Age: " + student.age + ", Grade: " + student.grade + "\n");
        }
    }

    private void deleteStudent() {
        String name = JOptionPane.showInputDialog("Enter student name to delete:");
        for (Student student : studentList) {
            if (student.name.equalsIgnoreCase(name)) {
                studentList.remove(student);
                textArea.append("Student deleted: " + student.name + "\n");
                return;
            }
        }
        textArea.append("Student not found: " + name + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRecordSystem().setVisible(true);
            }
        });
    }
}
