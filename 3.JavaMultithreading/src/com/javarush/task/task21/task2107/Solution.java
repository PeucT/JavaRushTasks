package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (! (o instanceof Solution)) return false;
        Solution solution = (Solution) o;

        if (users != null) {
            if (solution.users == null) return false;
            else {
                for (Map.Entry<String, User> entry : users.entrySet()) {
                    if (!solution.users.containsKey(entry.getKey())) return false;
                }
            }
        } else {
            if (solution.users == null) return true;
            else return false;
        }
        return users != null ? users.equals(solution.users) : solution.users == null;

    }

    @Override
    public int hashCode() {
        return users != null ? users.hashCode() : 0;
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution cloned = new Solution();

        for (Map.Entry<String, User> entry : users.entrySet()) {
            User clonedUser = (User) entry.getValue().clone();
            cloned.users.put(entry.getKey(), clonedUser);
        }
        return cloned;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            User clone = new User(this.age, this.name);
            return clone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (age != user.age) return false;
            return name != null ? name.equals(user.name) : user.name == null;

        }

        @Override
        public int hashCode() {
            int result = age;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
