import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long youngPerson = persons.stream().filter(x -> x.getAge() < 18).count();
        List<String> lastName = persons.stream().filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(s -> s.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .toList();
        List<Person> Worker = persons.stream().filter(s -> s.getSex().equals(Sex.WOMAN) ?
                        s.getAge() >= 18 && s.getAge() <= 60 : s.getAge() >= 18 && s.getAge() <= 65)
                .filter(s -> s.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        while (true) {
            System.out.println("""
                    Выберите команду :
                    1.Кол-во несовершеннолетних людей\s
                    2.Список фамилий призывников
                    3.Список трудоспособных людей с высшим образованием
                    0.Завершить работу""");

            int option = scanner.nextInt();
            switch (option) {
                case 1 -> System.out.println(youngPerson);
                case 2 -> lastName.forEach(System.out::println);
                case 3 -> Worker.forEach(System.out::println);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown command");

            }
        }
    }
}
