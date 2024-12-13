package vending;

public class Cash {
    private Integer d100;
    private Integer d50;
    private Integer d20;
    private Integer d10;
    private Integer d5;
    private Integer d2;
    private Integer d1;
    private Integer c50;
    private Integer c20;
    private Integer c10;
    private Integer c5;
    public Cash(){}
    public Cash(Integer d100, Integer d50, Integer d20, Integer d10, Integer d5, Integer d2, Integer d1, Integer c50, Integer c20, Integer c10, Integer c5) {
        this.d100 = d100;
        this.d50 = d50;
        this.d20 = d20;
        this.d10 = d10;
        this.d5 = d5;
        this.d2 = d2;
        this.d1 = d1;
        this.c50 = c50;
        this.c20 = c20;
        this.c10 = c10;
        this.c5 = c5;
    }

    public Integer getD100() {
        return this.d100;
    }

    public Integer getD50() {
        return this.d50;
    }

    public Integer getD20() {
        return this.d20;
    }

    public Integer getD10() {
        return this.d10;
    }

    public Integer getD5() {
        return this.d5;
    }

    public Integer getD2() {
        return this.d2;
    }

    public Integer getD1() {
        return this.d1;
    }

    public Integer getC50() {
        return this.c50;
    }

    public Integer getC20() {
        return this.c20;
    }

    public Integer getC10() {
        return this.c10;
    }

    public Integer getC5() {
        return this.c5;
    }

    public Integer get(Double denomination) {
        if (denomination == 100.0) {
            return this.d100;
        } else if (denomination == 50.0) {
            return this.d50;
        } else if (denomination == 20.0) {
            return this.d20;
        } else if (denomination == 10.0) {
            return this.d10;
        } else if (denomination == 5.0) {
            return this.d5;
        } else if (denomination == 2.0) {
            return this.d2;
        } else if (denomination == 1.0) {
            return this.d1;
        } else if (denomination == 0.5) {
            return this.c50;
        } else if (denomination == 0.2) {
            return this.c20;
        } else if (denomination == 0.1) {
            return this.c10;
        } else if (denomination == 0.05) {
            return this.c5;
        }
        return null;
    }

    public void less(Double denomination, Integer amount) {
        if (denomination == 100.0) {
            this.d100 -= amount;
        } else if (denomination == 50.0) {
            this.d50 -= amount;
        } else if (denomination == 20.0) {
            this.d20 -= amount;
        } else if (denomination == 10.0) {
            this.d10 -= amount;
        } else if (denomination == 5.0) {
            this.d5 -= amount;
        } else if (denomination == 2.0) {
            this.d2 -= amount;
        } else if (denomination == 1.0) {
            this.d1 -= amount;
        } else if (denomination == 0.5) {
            this.c50 -= amount;
        } else if (denomination == 0.2) {
            this.c20 -= amount;
        } else if (denomination == 0.1) {
            this.c10 -= amount;
        } else if (denomination == 0.05) {
            this.c5 -= amount;
        }
    }

    public void add(Double denomination, Integer amount) {
        if (denomination == 100.0) {
            this.d100 += amount;
        } else if (denomination == 50.0) {
            this.d50 += amount;
        } else if (denomination == 20.0) {
            this.d20 += amount;
        } else if (denomination == 10.0) {
            this.d10 += amount;
        } else if (denomination == 5.0) {
            this.d5 += amount;
        } else if (denomination == 2.0) {
            this.d2 += amount;
        } else if (denomination == 1.0) {
            this.d1 += amount;
        } else if (denomination == 0.5) {
            this.c50 += amount;
        } else if (denomination == 0.2) {
            this.c20 += amount;
        } else if (denomination == 0.1) {
            this.c10 += amount;
        } else if (denomination == 0.05) {
            this.c5 += amount;
        }
    }

    public void modify(String denomination, Integer amount) {
        if (denomination.equals("100$")) {
            this.d100 = amount;
        } else if (denomination.equals("50$")) {
            this.d50 = amount;
        } else if (denomination.equals("20$")) {
            this.d20 = amount;
        } else if (denomination.equals("10$")) {
            this.d10 = amount;
        } else if (denomination.equals("5$")) {
            this.d5 = amount;
        } else if (denomination.equals("2$")) {
            this.d2 = amount;
        } else if (denomination.equals("1$")) {
            this.d1 = amount;
        } else if (denomination.equals("50c")) {
            this.c50 = amount;
        } else if (denomination.equals("20c")) {
            this.c20 = amount;
        } else if (denomination.equals("10c")) {
            this.c10 = amount;
        } else if (denomination.equals("5c")) {
            this.c5 = amount;
        }
    }
}
