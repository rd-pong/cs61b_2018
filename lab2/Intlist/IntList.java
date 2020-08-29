import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 * [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    // http://www.pythontutor.com/java.html#code=public%20class%20IntList%20%7B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20First%20element%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20int%20first%3B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Remaining%20elements%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%20rest%3B%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20first%20FIRST0%20and%20rest%20REST0.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28int%20first0,%20IntList%20rest0%29%20%7B%0A%20%20%20%20%20%20%20%20first%20%3D%20first0%3B%0A%20%20%20%20%20%20%20%20rest%20%3D%20rest0%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20null%20rest,%20and%20first%20%3D%200.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28%29%20%7B%0A%20%20%20%20%20%20%20%20/*%20NOTE%3A%20public%20IntList%20%28%29%20%7B%20%7D%20%20would%20also%20work.%20*/%0A%20%20%20%20%20%20%20%20this%280,%20null%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20of%28Integer...%20args%29%20%7B%0A%20%20%20%20%20%20%20%20IntList%20result,%20p%3B%0A%0A%20%20%20%20%20%20%20%20if%20%28args.length%20%3E%200%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20result%20%3D%20new%20IntList%28args%5B0%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%0A%20%20%20%20%20%20%20%20int%20k%3B%0A%20%20%20%20%20%20%20%20for%20%28k%20%3D%201,%20p%20%3D%20result%3B%20k%20%3C%20args.length%3B%20k%20%2B%3D%201,%20p%20%3D%20p.rest%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20p.rest%20%3D%20new%20IntList%28args%5Bk%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20result%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20squareListIterative%28IntList%20L%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28L%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20IntList%20res%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20IntList%20ptr%20%3D%20res%3B%0A%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20while%20%28L%20!%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr.rest%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr%20%3D%20ptr.rest%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20res%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%7B%0A%20%20%20%20%20%20IntList%20L%20%3D%20IntList.of%281,%202,%203,%204,%205%29%3B%0A%20%20%20%20%20%20squareListIterative%28L%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%7D&cumulative=false&curInstr=106&heapPrimitives=nevernest&mode=display&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        //TODO: Recursive
        // http://www.pythontutor.com/java.html#code=public%20class%20IntList%20%7B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20First%20element%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20int%20first%3B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Remaining%20elements%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%20rest%3B%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20first%20FIRST0%20and%20rest%20REST0.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28int%20first0,%20IntList%20rest0%29%20%7B%0A%20%20%20%20%20%20%20%20first%20%3D%20first0%3B%0A%20%20%20%20%20%20%20%20rest%20%3D%20rest0%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20null%20rest,%20and%20first%20%3D%200.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28%29%20%7B%0A%20%20%20%20%20%20%20%20/*%20NOTE%3A%20public%20IntList%20%28%29%20%7B%20%7D%20%20would%20also%20work.%20*/%0A%20%20%20%20%20%20%20%20this%280,%20null%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20of%28Integer...%20args%29%20%7B%0A%20%20%20%20%20%20%20%20IntList%20result,%20p%3B%0A%0A%20%20%20%20%20%20%20%20if%20%28args.length%20%3E%200%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20result%20%3D%20new%20IntList%28args%5B0%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%0A%20%20%20%20%20%20%20%20int%20k%3B%0A%20%20%20%20%20%20%20%20for%20%28k%20%3D%201,%20p%20%3D%20result%3B%20k%20%3C%20args.length%3B%20k%20%2B%3D%201,%20p%20%3D%20p.rest%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20p.rest%20%3D%20new%20IntList%28args%5Bk%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20result%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20squareListIterative%28IntList%20L%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28L%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20IntList%20res%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20IntList%20ptr%20%3D%20res%3B%0A%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20while%20%28L%20!%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr.rest%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr%20%3D%20ptr.rest%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20res%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20catenate%28IntList%20A,%20IntList%20B%29%20%7B%0A%20%20%20%20%20%20%20%20//TODO%3A%20Recursive%0A//%20%20%20%20%20%20%20%20if%20%28A%20%3D%3D%20null%29%20%7B%0A//%20%20%20%20%20%20%20%20%20%20%20%20return%20B%3B%0A//%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A//%20%20%20%20%20%20%20%20%20%20%20%20IntList%20res%20%3D%20new%20IntList%28A.first,%20catenate%28A.rest,%20B%29%29%3B%0A//%20%20%20%20%20%20%20%20%20%20%20%20return%20res%3B%0A//%20%20%20%20%20%20%20%20%7D%0A%0A%20%20%20%20%20%20%20%20//TODO%3A%20Iterative%0A%20%20%20%20%20%20%20%20if%20%28A%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20IntList%20res%20%3D%20new%20IntList%28A.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20IntList%20ptr%20%3D%20res%3B%0A%20%20%20%20%20%20%20%20A%20%3D%20A.rest%3B%0A%20%20%20%20%20%20%20%20while%20%28A%20!%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr.rest%20%3D%20new%20IntList%28A.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20A%20%3D%20A.rest%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr%20%3D%20ptr.rest%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20ptr.rest%20%3D%20B%3B%0A%20%20%20%20%20%20%20%20return%20res%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20dcatenate%28IntList%20A,%20IntList%20B%29%20%7B%0A%20%20%20%20%20%20%20%20//TODO%3A%20Recursive%0A%20%20%20%20%20%20%20%20if%20%28A%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20A%20%3D%20B%3B%0A%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20A.rest%20%3D%20dcatenate%28A.rest,%20B%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20A%3B%0A%20%20%20%20%7D%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%7B%0A%20%20%20%20%20%20IntList%20A%20%3D%20IntList.of%281,%202,%203%29%3B%0A%20%20%20%20%20%20IntList%20B%20%3D%20IntList.of%284,%205,%206%29%3B%0A%20%20%20%20%20%20IntList.dcatenate%28A,%20B%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%7D&cumulative=false&curInstr=108&heapPrimitives=nevernest&mode=display&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false
//        if (A == null) {
//            A = B;
//        } else {
//            A.rest = dcatenate(A.rest, B);
//        }
//        return A;
        //TODO: Iterative
        if (A == null) {
            return null;
        }else if(A.rest == null){
            A.rest = B;
            return A;
        }
        IntList ptr = A.rest;
        while (ptr.rest != null) {
            ptr = ptr.rest;
        }
        ptr.rest = B;
        return A;
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        //TODO: Recursive
//        if (A == null) {
//            return B;
//        } else {
//            IntList res = new IntList(A.first, catenate(A.rest, B));
//            return res;
//        }

        //TODO: Iterative
        // http://www.pythontutor.com/java.html#code=public%20class%20IntList%20%7B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20First%20element%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20int%20first%3B%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Remaining%20elements%20of%20list.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%20rest%3B%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20first%20FIRST0%20and%20rest%20REST0.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28int%20first0,%20IntList%20rest0%29%20%7B%0A%20%20%20%20%20%20%20%20first%20%3D%20first0%3B%0A%20%20%20%20%20%20%20%20rest%20%3D%20rest0%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20A%20List%20with%20null%20rest,%20and%20first%20%3D%200.%0A%20%20%20%20%20*/%0A%20%20%20%20public%20IntList%28%29%20%7B%0A%20%20%20%20%20%20%20%20/*%20NOTE%3A%20public%20IntList%20%28%29%20%7B%20%7D%20%20would%20also%20work.%20*/%0A%20%20%20%20%20%20%20%20this%280,%20null%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20of%28Integer...%20args%29%20%7B%0A%20%20%20%20%20%20%20%20IntList%20result,%20p%3B%0A%0A%20%20%20%20%20%20%20%20if%20%28args.length%20%3E%200%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20result%20%3D%20new%20IntList%28args%5B0%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%0A%20%20%20%20%20%20%20%20int%20k%3B%0A%20%20%20%20%20%20%20%20for%20%28k%20%3D%201,%20p%20%3D%20result%3B%20k%20%3C%20args.length%3B%20k%20%2B%3D%201,%20p%20%3D%20p.rest%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20p.rest%20%3D%20new%20IntList%28args%5Bk%5D,%20null%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20result%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20IntList%20squareListIterative%28IntList%20L%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28L%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20IntList%20res%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20IntList%20ptr%20%3D%20res%3B%0A%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20while%20%28L%20!%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr.rest%20%3D%20new%20IntList%28L.first%20*%20L.first,%20null%29%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20L%20%3D%20L.rest%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20ptr%20%3D%20ptr.rest%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20res%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%7B%0A%20%20%20%20%20%20IntList%20L%20%3D%20IntList.of%281,%202,%203,%204,%205%29%3B%0A%20%20%20%20%20%20squareListIterative%28L%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%7D&cumulative=false&curInstr=0&heapPrimitives=nevernest&mode=display&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false
        if (A == null) {
            return null;
        }
        IntList res = new IntList(A.first, null);
        IntList ptr = res;
        A = A.rest; // In this step, A(the local variable in this method)'s reference is changed from A (outside pass in) to A.rest, that is why A outside this function stay intact.
        while (A != null) {
            ptr.rest = new IntList(A.first, null);
            A = A.rest;
            ptr = ptr.rest;
        }
        ptr.rest = B;
        return res;
    }


    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

