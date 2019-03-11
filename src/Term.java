public class Term implements Comparable {
    private double coefficient;
    private int power;
    private String termSign;

    public Term(String term) {
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

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    private Double parseCoefficient(String term) {
        if (term.charAt(0) == 'X')
            return 1.0;
        for (int i = 0; i < term.length(); i++) {
            if (term.charAt(i) == 'X' && termSign.equals("+"))
                return (Double.parseDouble(term.substring(0, i))) * 1.0;
            else if (term.charAt(i) == 'X' && termSign.equals("-"))
                return (Double.parseDouble(term.substring(0, i)) - (Double.parseDouble(term.substring(0, i)) * 2)) * 1.0;
        }
            return 0.0;
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
        String term = "";

        if (power == 0)
            return Double.toString(coefficient);
        if (coefficient != 1)
            term += Double.toString(coefficient) + "X";
        else
            term += "X";
        if (power != 1)
            term += "^" + Integer.toString(power);

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