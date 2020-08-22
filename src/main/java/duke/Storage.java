package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.task.*;
import duke.exception.*;

public class Storage {

    private Path path;

    Storage(String filePath) {
        try {
            this.path = Paths.get(filePath);
            if (!Files.exists((path))) {
                // If .txt file does not exist, create the file
                File file = new File(filePath);
                Path directory = file.getParentFile().toPath();
                if (!Files.exists(directory)) {
                    Files.createDirectory(directory);
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    List<Task> load() throws DukeFileNotFoundException {
        File f = new File(String.valueOf(path));
        try {
            Scanner s = new Scanner(f);
            List<Task> taskList = new ArrayList<>();
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ");
                switch (task[0]) {
                    case "T": {
                        taskList.add(new Todo(task[2], task[1].equals("1")));
                        break;
                    }
                    case "E": {
                        taskList.add(new Event(task[2], task[3], task[1].equals("1")));
                        break;
                    }
                    case "D": {
                        taskList.add(new Deadline(task[2], task[3], task[1].equals("1")));
                        break;
                    }
                }
            }
            s.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }
    }

    String parseTaskAsText(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.done ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Event) {
            return "E | " + (task.done ? "1" : "0") + " | " + task.description + " | " + ((Event) task).getDate();
        } else {
            return "D | " + (task.done ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).getDate();
        }
    }

    public void save(TaskList taskList) throws DukeLoadingErrorException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path));
            String content = "";
            for(Task task : taskList.getList()) {
                content += parseTaskAsText(task) + "\n";
            }
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new DukeLoadingErrorException();
        }
    }


}
