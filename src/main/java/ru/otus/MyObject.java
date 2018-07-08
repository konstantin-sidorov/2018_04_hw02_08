package ru.otus;


public class MyObject {
    private double item;
    private boolean flag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyObject object = (MyObject) o;

        if (Double.compare(object.item, item) != 0) return false;
        return flag == object.flag;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(item);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (flag ? 1 : 0);
        return result;
    }

    public MyObject(double item , boolean flag) {
        this.item = item;
        this.flag = flag;

    }
}
