import java.util.*;

public class PhoneBook {

    private static final String REGEX_PHONE = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final String REGEX_NAME = "^([А-Я]{1}[а-яё]{1,23})$";
    Map<String, String> phoneMap = new HashMap<>();


    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона (отдельные методы для проверки)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (checkPhone(phone) && checkName(name)) {
            if (phoneMap.containsKey(name)) {
                phoneMap.put(name, phoneMap.get(name).concat(", ").concat(phone));
            } else if (phoneMap.containsValue(phone)) {
                for (String key: phoneMap.keySet()) {
                    if(phoneMap.get(key).equals(phone)){
                        phoneMap.remove(key);
                        phoneMap.put(name, phone);
                    }
                }
            } else {
                phoneMap.put(name, phone);
            }
        }
    }


    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        for (String key : phoneMap.keySet()) {
            if (phoneMap.get(key).equals(phone)) {
                return key + " - " + phoneMap.get(key);
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        Set<String> treeSet = new TreeSet<>();
        for (String key : phoneMap.keySet()) {
            if (key.equals(name)) {
                treeSet.add(key + " - " + phoneMap.get(key));
                return treeSet;
            }
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> treeSet1 = new TreeSet<>();
        if(!phoneMap.isEmpty()){
            for(String key : phoneMap.keySet()){
                treeSet1.add(key + " - " + phoneMap.get(key));
            }
            return treeSet1;
        }
        return new TreeSet<>();
    }

    public boolean checkPhone(String phone) {
        return phone.matches(REGEX_PHONE);
    }

    public boolean checkName(String name) {
        return name.matches(REGEX_NAME);
    }
    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}