package frontend;

import backend.DBMethods;

import java.util.Scanner;

/**
 * Created by Dovashar on 16.10.2016.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final NotebookHelper helper = new NotebookHelper();

    public static void main(String[] args) {
        helper.deleteAllNotes();
        do {
            System.out.println();
            System.out.println("Please specify an option you wish with your notepad: \n " +
                    "1) Add a Note;\n 2) List all Notes;\n " +
                    "3) Find Note with specific parameters; \n 4)Clear notebook; \n 5) Exit;");
            switch (input.nextLine()) {
                case "1":
                    System.out.println("Please specify note values: Tag and Text");
                    helper.addNote(input.nextLine(), input.nextLine());
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Printing all available notes");
                    System.out.println();
                    helper.printAllNotes();
                    break;
                case "3":
                    System.out.println("Searching a note");
                    System.out.println();
                    createSearchConditions();
                    break;
                case "4":
                    System.out.println("Deleting all notes");
                    System.out.println();
                    helper.deleteAllNotes();
                    break;
                case "5":
                    return;
            }
        } while (true);
    }

    private static void createSearchConditions() {
        helper.cleanSearchCondition();
        System.out.println();
        System.out.println("Please specify or press enter if none a date");
        String value = input.nextLine();
        if (!value.isEmpty()) {
            helper.buildSearchCondition("timestamp", value);
        }
        System.out.println();
        System.out.println("Please specify tag");
        value = input.nextLine();
        if (!value.isEmpty()) {
            helper.buildSearchCondition("tag", value);
        }
        System.out.println();
        System.out.println("Please specify text");
        value = input.nextLine();
        if (!value.isEmpty()) {
            helper.buildSearchCondition("text", value);
        }
        helper.printNotesViaSearch();
        System.out.println();
        System.out.println("Do you want to delete this note? (Y/N)");
        if (input.nextLine().toUpperCase().equals("Y")) helper.deleteFoundNote();
        helper.cleanSearchCondition();
    }
}
