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
        SinglyLinkedList resultList = new SinglyLinkedList();
        for (Node thisPointer = list.getHead(); thisPointer != null; thisPointer = thisPointer.getNext()) {
            Term thisPointerTerm = (Term) thisPointer.getData();
            for (Node pPointer = p.getList().getHead(); pPointer != null; pPointer = pPointer.getNext()) {
                Term pPointerTerm = (Term) pPointer.getData();
                if (thisPointerTerm.getPower() == pPointerTerm.getPower()) {
                    int newCoeff = thisPointerTerm.getCoefficient() + pPointerTerm.getCoefficient();
                    String newTerm = Integer.toString(newCoeff) + "X^" + thisPointerTerm.getPower();
                    resultList.insert(new Node(newTerm));
                }
                else if (thisPointerTerm.getPower() > pPointerTerm.getPower()) {
                    resultList.insert(new Node(thisPointerTerm));
                    thisPointer = thisPointer.getNext();
                }
                else if (thisPointerTerm.getPower() < pPointerTerm.getPower())
                    resultList.insert(new Node(pPointerTerm));
            }
            resultList.insert(new Node(thisPointerTerm));
        }
        String result = "";
        Term resultTerm = (Term) resultList.getHead().getData();
        result += resultTerm.toString();
        for (Node resultPointer = resultList.getHead().getNext(); resultPointer != null; resultPointer = resultPointer.getNext()) {

        }
//
//        String sumResult = "";
//        Node mainPointer, secondaryPointer;
//        Term mainTerm, secondaryTerm;
//
//        if (list.size() > p.getList().size()) {
//            mainPointer = list.getHead();
//            secondaryPointer = p.getList().getHead();
//        }
//        else {
//            mainPointer = p.getList().getHead();
//            secondaryPointer = list.getHead();
//        }
//
//        while (mainPointer.getData() != null) {
//            mainTerm = (Term) mainPointer.getData();
//            if (secondaryPointer.getData() != null)
//                secondaryTerm = (Term) secondaryPointer.getData();
//            else
//                secondaryTerm = null;
//
//            if (mainTerm.getPower() == secondaryTerm.getPower()) {
//                int sum = mainTerm.getCoefficient() + secondaryTerm.getCoefficient();
//                if (sum >= 0)
//                sumResult += sum + "X^" + mainTerm.getPower();
//                mainPointer = mainPointer.getNext();
//                secondaryPointer = secondaryPointer.getNext();
//            }
//            else if (mainTerm.getPower() > secondaryTerm.getPower() || secondaryTerm == null) {
//                sumResult += mainTerm.toString();
//                mainPointer = mainPointer.getNext();
//            }
//            else {
//                sumResult += secondaryTerm.toString();
//                secondaryPointer = secondaryPointer.getNext();
//            }
//        }
//        return new Polynomial(sumResult);
//
//        if (p.getList().size() > size)
//            size = p.getList().size();
//
//        for (int i = 0; i <= size; i++) {
//            if (polyTerm1 == null) {
//                sumResult += polyTerm2.toString();
//            }
//            else if (polyTerm2 == null)
//                sumResult += polyTerm1.toString();
//            else if (polyTerm1.getPower() > polyTerm2.getPower())
//                sumResult += polyTerm1.toString();
//            else if (polyTerm2.getPower() > polyTerm1.getPower())
//                sumResult += polyTerm2.toString();
//            else {
//                int sum = polyTerm1.getCoefficient() + polyTerm2.getCoefficient();
//                sumResult += sum + "X^" + polyTerm1.getPower();
//            }
        // }
    }

    @Override
    public Polynomial subtract(Polynomial p) {
        return null;
    }

    @Override
    public Polynomial multiply(Polynomial p) {
        return null;
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