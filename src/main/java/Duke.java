import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException{
        System.out.println("Hello from Gambit, how may I assist you today?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while(sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                break;
            } else if (word.equals("list")) {
                int size = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }
            } else if (word.contains("done")) {
                String strArray[] = word.split(" ");
                int value =Integer.parseInt(strArray[1]);
                Task complete = list.get(value - 1);
                complete.markAsDone();
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println(complete.toString());
            } else if ((word.contains("todo")) || (word.contains("event")) || (word.contains("deadline"))) {
                if (word.contains("todo")) {
                    String save = word.replaceAll("todo","");
                    ToDo t = new ToDo(save);
                    if (save.equals("")) {
                        Thread.setDefaultUncaughtExceptionHandler((u, e) -> System.err.println(e.getMessage()));
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    list.add(t);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(t.toString());
                } else if (word.contains("event")) {
                    String[] info = word.split("/at");
                    info[0] = info[0].replaceAll("event","");
                    Event e = new Event(info[0],info[1]);
                    list.add(e);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(e.toString());
                } else if (word.contains("deadline")) {
                    String[] input = word.split("/by");
                    input[0] = input[0].replaceAll("deadline", "");
                    Deadline d = new Deadline(input[0], input[1]);
                    list.add(d);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(d.toString());
                }
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else {
                Thread.setDefaultUncaughtExceptionHandler((u, e) -> System.err.println(e.getMessage()));
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
