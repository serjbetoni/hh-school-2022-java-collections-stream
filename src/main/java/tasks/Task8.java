package tasks;

import common.Person;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  /*
  Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  ________________________________________________________________________________
  Удалена проверка размера списка и добавлен метод skip вместо удаления
   */
  public List<String> getRealNames(List<Person> persons) {
    return persons.stream()
        .skip(1)
        .map(Person::getFirstName)
        .toList();
  }

  /*
  ну и различные имена тоже хочется
  ________________________________________________________________________________
  Возвращаем уникальное множество, изменено название метода
  */
  public Set<String> getUniquesNames(List<Person> persons) {
    return new HashSet<>(getRealNames(persons));
  }

  /*
  Для фронтов выдадим полное имя, а то сами не могут
  ________________________________________________________________________________
  Добавлено отчество, использован джоин, изменено название метода
  */
  public String getStringWithFullName(Person person) {
    return String.join(" ", person.getFirstName(), person.getMiddleName(), person.getSecondName());
  }

  /*
  словарь id персоны -> ее имя
  ________________________________________________________________________________
  Использован стрим, изменено название метода
  */
  public Map<Integer, String> getPersonIdByNames(Collection<Person> persons) {
    return persons.stream()
        .collect(Collectors.toMap(Person::getId, Person::getFirstName, (person, person2) -> person));
  }

  /*
  есть ли совпадающие в двух коллекциях персоны?
  ________________________________________________________________________________
  Использован стрим, изменено название метода
  */
  public boolean assertSamePersonsInCollections(Collection<Person> persons1, Collection<Person> persons2) {
    return persons1.stream()
        .anyMatch(new HashSet<>(persons2)::contains);
  }

  /*
  ...
  ________________________________________________________________________________
  Использован стрим, метод для подсчета четных чисел
  */
  public long countEven(Stream<Integer> numbers) {
    return numbers
        .filter(num -> num % 2 == 0)
        .count();
  }
}
