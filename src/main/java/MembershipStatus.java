public class MembershipStatus extends Member {

    private boolean paidOrNot;

    // Betaling
    public MembershipStatus(int memberNumber, boolean paidOrNot) {
        super(memberNumber, paidOrNot);

        initializePaidOrNot(paidOrNot);
    }

    private void initializePaidOrNot(boolean paidOrNot) {
        this.paidOrNot = paidOrNot;
    }

    public boolean isPaidOrNot() {
        return paidOrNot;
    }


    @Override
    public String toString() {
        return "Member{" +
                "paid status=" + paidOrNot +
                '}';
    }
}