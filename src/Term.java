public class Term implements Comparable {
    private int coefficient, power;
    private String termSign, term;

    public Term(String term) {
        this.term = term;
        if (term.charAt(0) == '-') {
            termSign = "-";
            term = term.substring(1);
        }
        else if (term.charAt(0) == '+') {
            termSign = "+";
            term = term.substring(1);
        }
        else
            termSign = "+";
        coefficient = parseCoefficient(term);
        power = parsePower(term);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    private int parseCoefficient(String term) {
        if (term.charAt(0) == 'X')
            return 1;
        for (int i = 0; i < term.length(); i++) {
            if (term.charAt(i) == 'X' && termSign.equals("+"))
                return Integer.parseInt(term.substring(0, i));
            else if (term.charAt(i) == 'X' && termSign.equals("-"))
                return Integer.parseInt(term.substring(0, i)) - (Integer.parseInt(term.substring(0, i)) * 2);
        }
            return 0;
    }

    private int parsePower(String term) {
        int charIndex = 0;

        while (charIndex < term.length()) {
            if (term.charAt(charIndex) == '^')
                return Integer.parseInt(term.substring(charIndex + 1));
            charIndex++;
        }
        return 1;
    }

    public String toString() {
        return term;
    }

    @Override
    public int compareTo(Object o) {
        Term otherTerm = (Term) o;

        if (this.power > otherTerm.getPower())
            return 1;
        if (this.power == otherTerm.getPower())
            if (this.coefficient > otherTerm.getCoefficient())
                return 1;
        return 0;
    }
}