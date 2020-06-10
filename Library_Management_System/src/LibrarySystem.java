import java.util.ArrayList;
import java.util.Scanner;

/***
 * Java doc ve yorumlar eklenecek
 */
public class LibrarySystem {
    private ArrayList<Studeent> students;
    private ArrayList<Librarian> librarians;

    public LibrarySystem(){
        students = new ArrayList<>();
        librarians = new ArrayList<>();
    }
    public void signUp(){
        String type;
        String name;
        String surname;
        String ID;
        String password;

        Scanner sb = new Scanner(System.in);

        System.out.println("Are you student or librarian or exit");
        type = sb.next();
        if ((type.equals("student")) || (type.equals("librarian")) || type.equals("exit")){
            if (type.equals("exit"))
                mainMenu();
            else{
                System.out.println("Please! Enter the name");
                name = sb.next();
                System.out.println("Please! Enter the surname");
                surname = sb.next();
                System.out.println("Please! Enter the ID");
                ID = sb.next();
                System.out.println("Please! Enter the password");
                password = sb.next();

                if (type.equals("student")){
                    students.add(new Studeent(name,ID,surname,password));
                }else{
                    librarians.add(new Librarian(name,ID,surname,password));
                }
            }
        }else{
            System.out.println("Wrong entry");
            signUp();
        }
    }

    public User logIn(String type){
        User temp = IDControl(type,0);
        if (temp == null){
            return null;
        }else{
            boolean password = passwordControl(temp,0);

            if (password){
                return temp;
            }else{
                return null;
            }
        }

    }

    private boolean passwordControl(User temp,int i){
        Scanner sb = new Scanner(System.in);
        System.out.println("Please! entry password");
        String password = sb.next();

        if (i == 4){
            return false;
        }else{
            if (temp.getPassword().equals(password)){
                return true;
            }else{
                System.out.println("Wrong password. Kalan deneme hakkınız: " +(4-i-1));
                return passwordControl(temp,i+1);
            }
        }
    }

    private User IDControl(String type,int i){
        if(i == 4){
            return null;
        }

        Scanner sb = new Scanner(System.in);
        System.out.println("Please! Entry ID");
        String ID = sb.next();
        int index = 0;

        if (type.equals("student")){

            while (students.size()>index){
                if (students.get(index).getID().equals(ID)){
                    return  students.get(index);
                }
                index++;
            }

        }else{
            while (librarians.size()>index){
                if (librarians.get(index).getID().equals(ID)){
                    return  librarians.get(index);
                }
                index++;
            }
        }
        System.out.println("Wrong ID. Kalan deneme hakkınız: " +(4-i-1));
        return IDControl(type,i+1);
    }

    public void mainMenu(){
        Scanner sb = new Scanner(System.in);
        System.out.println("signUp or logIn or exit");
        String type = sb.next();
        switch (type){
            case "signUp" :
                signUp();
                mainMenu();
                break;
            case "logIn":
                System.out.println("Are you student or librarian or exit");
                type = sb.next();
                switch (type){
                    case "student":
                        studentMenu(type);
                        break;
                    case "librarian":
                        librarianMenu(type);
                        break;
                    case "exit":
                        break;
                    default:
                        System.out.println("Wrong entry");
                        mainMenu();
                        break;
                }
            case "exit":
                break;
            default:
                System.out.println("Wrong entry");
                mainMenu();
                break;
        }

    }
    private void studentMenu(String type){
        Studeent temp = (Studeent) logIn(type);
        if (temp != null) {
            System.out.println(temp.toString());
        }
        mainMenu();
    }
    private void librarianMenu(String type){
        Librarian temp = (Librarian) logIn(type);
        if (temp != null) {
            System.out.println(temp.toString());
        }
        mainMenu();
    }

    public void show(){
        for (int i = 0; i <students.size() ; i++) {
            System.out.println(students.get(i).toString());
        }
        for (int i = 0; i <librarians.size() ; i++) {
            System.out.println(librarians.get(i).toString());
        }
    }
}
