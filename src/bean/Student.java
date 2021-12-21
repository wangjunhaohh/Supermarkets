package bean;

public class Student {
    String name;
    int num;
    String sex;
    int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int num,String name , int age,String sex) {
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "学号='" + num + '\'' +
                ", 姓名=" + name +
                ", 年龄='" + age + '\'' +
                ", 性别=" + sex;
    }
}
