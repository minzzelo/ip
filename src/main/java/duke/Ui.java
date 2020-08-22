package duke;

import java.util.Scanner;
import duke.task.*;

public class Ui {

    Scanner s;

    Ui() {
        s = new Scanner(System.in);
    }

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            String message = String.valueOf(i) + ".";
            message += task;
            System.out.println(message);
        }
    }

    String readCommand() {
        return s.nextLine();
    }
}
