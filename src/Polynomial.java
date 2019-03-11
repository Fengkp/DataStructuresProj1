public class Polynomial implements PolynomialInterface {
    private SinglyLinkedList list = new SinglyLinkedList();
    private String polynomial;

    public Polynomial(String s) {
        polynomial = s;
        int charIndex = 0;

        while (charIndex < s.length()) {
            if ((s.charAt(charIndex) == '+' || s.charAt(charIndex) == '-') && charIndex != 0) {
                list.insert(new Node(new Term(s.substring(0, charIndex))));
                s = s.substring(charIndex);
                charIndex = -1;
            }
            if (charIndex + 1 == s.length())        // End of equation
                list.insert(new Node(new Term(s.substring(0, s.length()))));
            charIndex++;
        }
    }

    public SinglyLinkedList getList() {
        return list;
    }

    @Override
    public Polynomial add(Polynomial p) {
        String result = "";
        Node pPointer = p.getList().getHead();
        Term thisPointerTerm;
        Term pPointerTerm;

        for (Node thisPointer = list.getHead(); thisPointer != null; thisPointer = thisPointer.getNext()) {
            thisPointerTerm = (Term) thisPointer.getData();
            pPointerTerm = (Term) pPointer.getData();
            if (thisPointerTerm.getPower() == pPointerTerm.getPower()) {
                double newCoeff = thisPointerTerm.getCoefficient() + pPointerTerm.getCoefficient();
                if (newCoeff > 0) {
                    result += "+" + Double.toString(newCoeff) + "X^" + thisPointerTerm.getPower();
                    pPointer = pPointer.getNext();
                }
                else if (newCoeff < 0) {
                    result += "-" + Double.toString(newCoeff) + "X^" + thisPointerTerm.getPower();
                    pPointer = pPointer.getNext();
                }
                else // newCoeff is 0; Terms cancel out;
                    pPointer = pPointer.getNext();
            }
            else if (pPointerTerm.getPower() > thisPointerTerm.getPower()) {
                if (pPointerTerm.getCoefficient() > 0)
                    result += "+" + pPointerTerm.toString();
                else
                    result += "-" + pPointerTerm.toString();
                pPointer = pPointer.getNext();
            }
            else {
                if (thisPointerTerm.getCoefficient() > 0)
                    result += "+" + thisPointerTerm.toString();
                else
                    result += "-" + thisPointerTerm.toString();
            }
        }
        while (pPointer != null) {
            pPointerTerm = (Term) pPointer.getData();
            if (pPointerTerm.getCoefficient() > 0)
                result += "+" + pPointerTerm.toString();
            else
                result += "-" + pPointerTerm.toString();
            pPointer = pPointer.getNext();
        }
        if (result.charAt(0) == '+' || result.charAt(0) == '-')
            result = result.substring(1);
        return new Polynomial(result);
    }

    @Override
    public Polynomial subtract(Polynomial p) {
        String result = "";
        Node pPointer = p.getList().getHead();
        Term thisPointerTerm;
        Term pPointerTerm;

        for (Node thisPointer = list.getHead(); thisPointer != null; thisPointer = thisPointer.getNext()) {
            thisPointerTerm = (Term) thisPointer.getData();
            pPointerTerm = (Term) pPointer.getData();
            if (thisPointerTerm.getPower() == pPointerTerm.getPower()) {
                double newCoeff = thisPointerTerm.getCoefficient() - pPointerTerm.getCoefficient();
                if (newCoeff > 0) {
                    result += "+" + Double.toString(newCoeff) + "X^" + thisPointerTerm.getPower();
                    pPointer = pPointer.getNext();
                }
                else if (newCoeff < 0) {
                    result += "-" + Double.toString(newCoeff) + "X^" + thisPointerTerm.getPower();
                    pPointer = pPointer.getNext();
                }
                else // newCoeff is 0; Terms cancel out;
                    pPointer = pPointer.getNext();
            }
            else if (pPointerTerm.getPower() > thisPointerTerm.getPower()) {
                if (pPointerTerm.getCoefficient() > 0)
                    result += "-" + pPointerTerm.toString();
                else
                    result += "+" + pPointerTerm.toString();
                pPointer = pPointer.getNext();
            }
            else {
                if (thisPointerTerm.getCoefficient() > 0)
                    result += "+" + thisPointerTerm.toString();
                else
                    result += "-" + thisPointerTerm.toString();
            }
        }
        while (pPointer != null) {
            pPointerTerm = (Term) pPointer.getData();
            if (pPointerTerm.getCoefficient() < 0)
                result += "+" + pPointerTerm.toString();
            else
                result += "-" + pPointerTerm.toString();
            pPointer = pPointer.getNext();
        }
        if (result.charAt(0) == '+' || result.charAt(0) == '-')
            result = result.substring(1);
        return new Polynomial(result);
    }

    @Override
    public Polynomial multiply(Polynomial p) {
        String result = "";
        Term thisPointerTerm;
        Term pPointerTerm;

        // Multiply Terms
        for (Node thisPointer = list.getHead(); thisPointer != null; thisPointer = thisPointer.getNext()) {
            thisPointerTerm = (Term) thisPointer.getData();
            for (Node pPointer = p.getList().getHead(); pPointer != null; pPointer = pPointer.getNext()) {
                pPointerTerm = (Term) pPointer.getData();
                double newCoeff = thisPointerTerm.getCoefficient() * pPointerTerm.getCoefficient();
                int newPower = thisPointerTerm.getPower() + pPointerTerm.getPower();

                if (newCoeff > 0)
                    result += "+" + Double.toString(newCoeff) + "X^" + Integer.toString(newPower);
                else
                    result += "-" + Double.toString(newCoeff) + "X^" + Integer.toString(newPower);
            }
        }
        if (result.charAt(0) == '+' || result.charAt(0) == '-')
            result = result.substring(1);

        // Add like terms
        Polynomial resultPoly = new Polynomial(result);
        String newResult = "";
        Term resultTerm;
        Term resultTermAfter;
        double resultCoeff = 0;

        for (Node resultPointer = resultPoly.getList().getHead(); resultPointer != null; resultPointer = resultPointer.getNext()) {
            resultTerm = (Term) resultPointer.getData();
            resultCoeff += resultTerm.getCoefficient();
            if (resultPointer.getNext() != null) {
                resultTermAfter = (Term) resultPointer.getNext().getData();

                if (resultTerm.getPower() == resultTermAfter.getPower()) {
                    while (resultTerm.getPower() == resultTermAfter.getPower()) {
                        resultCoeff += resultTermAfter.getCoefficient();
                        resultPointer = resultPointer.getNext();
                        resultTerm = (Term) resultPointer.getData();
                        resultTermAfter = (Term) resultPointer.getNext().getData();
                    }
                }
            }
            if (resultCoeff > 0)
                newResult += "+" + Double.toString(resultCoeff) + "X^" + Integer.toString(resultTerm.getPower());
            else
                newResult += "-" + Double.toString(resultCoeff) + "X^" + Integer.toString(resultTerm.getPower());
            resultCoeff = 0;
        }
        if (newResult.charAt(0) == '+' || newResult.charAt(0) == '-')
            newResult = newResult.substring(1);
        return new Polynomial(newResult);
    }

    public Polynomial divide(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }
    public Polynomial remainder(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }

    public final String toString() {
        return polynomial;
    }
}