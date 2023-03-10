import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static final String path = "/src/test.txt";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static List<List<PlayerItem>> grid = new ArrayList<>();

    public static List<People> readFile() throws IOException {

        String filePath = new File("").getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(filePath + path));

        String str;
        List<People> list = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            String[] splited = str.split("\\s+");
            People people = new People(splited[0], splited[1], splited[2]);
            list.add(people);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\n___СТАРТ работы программы___\n");

        List<People> list = readFile();

        System.out.println("Участники успешно загружены");
        game(list);
        System.out.println("Турнирная сетка составлена");

        char choice;

        do {
            System.out.println("\nВыберите действие:\n\t1) Вывод списка участников турнира.\n" +
                    "\t2) Вывод турнирной сетки.\n\t3) Ручное редактирование.\n0) Выход.\n");

            Scanner s = new Scanner(System.in);
            choice = s.next().charAt(0);

            switch (choice) {
                case '1':
                    System.out.println("Полный список участников:\n" + list.toString());
                    break;
                case '2':
                    System.out.println("Турнирная сетка:\n" + grid.toString());
                    break;
                case '3':
                    System.out.println("\nВыберите номер этапа: ");
                    int chLvl = Integer.parseInt(reader.readLine());
                    System.out.println("Пары на этапе №" + chLvl + "\n" + grid.get(chLvl - 1));
                    List<PlayerItem> tmpItem = grid.get(chLvl - 1);

                    System.out.println("\nУкажите номер пары для редактирования: ");
                    int chItm = Integer.parseInt(reader.readLine());
                    System.out.println("Пара для редактирования №" + chItm + "\n" + tmpItem.get(chItm - 1));

                    System.out.println("\nУкажите победителя: ");
                    int chWn = Integer.parseInt(reader.readLine());
                    System.out.println("Выбран победитель №" + chWn);

                    // Переобозначаем победителя в паре
                    grid.get(chLvl - 1).get(chItm - 1).setWinner(chWn);

                    for (int i = grid.size() - 1; i >= chLvl; i--) {
                        grid.remove(i);
                    }
                    // вновь играем
                    game(GetListPeople(grid.get(chLvl - 1)));

                    System.out.println("Турнирная сетка успешно пересобрана!");
                    break;
                case '0':
                    break;
                default:
                    System.out.println("\nНекореектный выбор! Повторите действие.");
                    break;
            }
        } while (choice != '0');

        System.out.println("\n___КОНЕЦ РАБОТЫ ПРОГРАММЫ___");
    }

    public static void game(List<People> mainList) {
        int index = 0;
        List<PlayerItem> PlayerItems = new ArrayList<>();
        int count = mainList.size() / 2;
        for (int i = 0; i < count; i++) {
            People p = mainList.get(index);
            index++;
            People pp = mainList.get(index);
            index++;

            int winner = 1 + (int) (Math.random() * ((2 - 1) + 1));
            PlayerItem pi = new PlayerItem(p, pp, winner);

            PlayerItems.add(pi);
        }
        grid.add(PlayerItems);

        List<People> curWinner = GetListPeople(PlayerItems);

        if (curWinner.size() != 1) {
            game(curWinner);
        }
    }

    public static List<People> GetListPeople(List<PlayerItem> PlayerItems) {
        List<People> curWinner = new ArrayList<>();
        for (PlayerItem item : PlayerItems) {
            if (item.getWinner() == 1) {
                curWinner.add(item.getPlayer1());
            } else {
                curWinner.add(item.getPlayer2());
            }
        }
        return curWinner;
    }
}