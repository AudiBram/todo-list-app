public class App {

    public static String[] data = new String[10];
    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        viewShowToDo();
    }

    public static void showToDo() {
        for (var i = 0; i < data.length; i++) {
            var todo = data[i];
            var no = i + 1;

            if (todo != null) {
                System.out.println(no + ". " + todo);
            }
        }
    }

    public static void addToDo(String todo) {
        // check for full
        var isFull = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                isFull = false;
                break;
            }
        }

        // if full, resize to 2x
        if (isFull) {
            var temp = data;
            data = new String[data.length * 2];

            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }

        // add to position where data is null
        for (var i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = todo;
                break;
            }
        }
    }

    public static boolean deleteToDo(Integer num) {
        if ((num - 1) >= data.length) {
            return false;
        } else if (data[num - 1] == null) {
            return false;
        } else {
            data[num - 1] = null;
            for (int i = (num - 1); i < data.length; i++) {
                if (i == (data.length - 1)) {
                    data[i] = null;
                } else {
                    data[i] = data[i + 1];
                }
            }
        }
        return true;
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void viewShowToDo() {
        label:
        while (true) {
            showToDo();

            System.out.println("MENU : ");
            System.out.println("1. Add");
            System.out.println("2. Delete");
            System.out.println("x. Quit");

            var input = input("Choose");
            switch (input) {
                case "1":
                    viewAddToDo();
                    break;
                case "2":
                    viewRemoveToDo();
                    break;
                case "x":
                    break label;
                default:
                    System.out.println("Not Found!");
                    break;
            }
        }
    }

    public static void viewAddToDo() {
        System.out.println("ADD TODOLIST");

        var todo = input("Todo (x if wanna cancel)");

        if (todo.equals("x")) {
            // cancel
        } else {
            addToDo(todo);
        }
    }

    public static void viewRemoveToDo() {
        System.out.println("DELETE TODOLIST");

        var number = input("Put number to delete (x if wanna cancel)");

        if (number.equals("x")) {
            // cancel
        } else {
            boolean success = deleteToDo(Integer.valueOf(number));
            if (!success) {
                System.out.println("Aborting to delete todolist: " + number);
            }
        }
    }
}
