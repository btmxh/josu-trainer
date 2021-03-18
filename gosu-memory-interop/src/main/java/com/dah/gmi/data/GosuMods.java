package com.dah.gmi.data;

import java.util.Objects;

public class GosuMods {
    private int num;
    private String str;

    public GosuMods() {
    }

    public GosuMods(int num, String str) {
        this.num = num;
        this.str = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public GosuMods num(int num) {
        setNum(num);
        return this;
    }

    public GosuMods str(String str) {
        setStr(str);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GosuMods)) {
            return false;
        }
        GosuMods gosuMods = (GosuMods) o;
        return num == gosuMods.num && Objects.equals(str, gosuMods.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, str);
    }

    @Override
    public String toString() {
        return "{" +
            " num='" + getNum() + "'" +
            ", str='" + getStr() + "'" +
            "}";
    }
}
