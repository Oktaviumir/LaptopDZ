import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LaptopShop {
    private Set<Laptop> laptops;

    public LaptopShop() {
        laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public void filterLaptops(Map<Integer, Object> filters) {
        for (Laptop laptop : laptops) {
            boolean passesFilter = true;

            for (Integer key : filters.keySet()) {
                Object filterValue = filters.get(key);

                switch (key) {
                    case 1:
                        int ramFilter = (int) filterValue;
                        if (laptop.getRam() < ramFilter) {
                            passesFilter = false;
                        }
                        break;
                    case 2:
                        int hddFilter = (int) filterValue;
                        if (laptop.getHdd() < hddFilter) {
                            passesFilter = false;
                        }
                        break;
                    case 3:
                        String osFilter = (String) filterValue;
                        if (!laptop.getOs().equalsIgnoreCase(osFilter)) {
                            passesFilter = false;
                        }
                        break;
                    case 4:
                        String colorFilter = (String) filterValue;
                        if (!laptop.getColor().equalsIgnoreCase(colorFilter)) {
                            passesFilter = false;
                        }
                        break;
                    default:
                        System.out.println("Некорректный критерий фильтрации");

                }

                if (!passesFilter) {
                    break;
                }
            }

            if (passesFilter) {
                System.out.println("Модель: " + laptop.getBrand());
                System.out.println("ОЗУ: " + laptop.getRam() + "ГБ");
                System.out.println("Объем ЖД " + laptop.getHdd() + "ГБ");
                System.out.println("Операционная система: " + laptop.getOs());
                System.out.println("Цвет: " + laptop.getColor());
                System.out.println("----------------------------");
            }
        }
    }

    public static void main(String[] args) {
        LaptopShop shop = new LaptopShop();

        Laptop laptop1 = new Laptop("Acer", 8, 500, "Windows 10", "Черный");
        shop.addLaptop(laptop1);

        Laptop laptop2 = new Laptop("Asus", 16, 1000, "Windows 10", "Серебристый");
        shop.addLaptop(laptop2);

        Laptop laptop3 = new Laptop("Dell", 4, 250, "Ubuntu", "Черный");
        shop.addLaptop(laptop3);

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию: ");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.print(" ");
        int filterCriterion = scanner.nextInt();

        switch (filterCriterion) {
            case 1:
            case 2:
                System.out.println("Введите минимальное значение для указанного критерия: ");
                int filterValue = scanner.nextInt();
                filters.put(filterCriterion, filterValue);
                break;
            case 3:
            case 4:
                System.out.println("Введите значение для указанного критерия: ");
                String filterString = scanner.next();
                filters.put(filterCriterion, filterString);
                break;
            default:
                System.out.println("Некорректный критерий фильтрации");
        }

        scanner.close();

        shop.filterLaptops(filters);
    }

}