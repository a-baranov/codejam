import java.util.*;

public class Solution {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int tcn = sc.nextInt();
            for (int tc = 1; tc <= tcn; tc++) {
                int n = sc.nextInt();
                List<Party> parties = new ArrayList<>();
                for (int p = 0; p < n; p++) {
                    parties.add(new Party(sc.nextInt(), (char)('A' + p)));
                }

                System.out.println("Case #" + tc + ": " + solve(parties));
            }
        }
    }

    private static String solve(List<Party> parties) {
        sort(parties);
        StringBuilder sb = new StringBuilder();
        while (!parties.isEmpty()) {
            Party p0 = parties.get(0);
            Party p1 = parties.get(1);
            sb.append(p0.getName());
            if (p0.getP() == p1.getP() && (p0.getP() > 1 || parties.size() != 3)) {
                sb.append(p1.getName());
                p1.dec();
            }
            p0.dec();
            sb.append(" ");
            groom(parties);
            sort(parties);
        }
        return sb.toString();
    }

    private static void sort(List<Party> parties) {
        parties.sort(Comparator.comparingInt(Party::getP).reversed());
    }

    private static void groom(List<Party> parties) {
        while (!parties.isEmpty() && parties.get(0).getP() == 0) {
            parties.remove(0);
        }
    }

    private final static class Party {
        private int p;
        private char name;

        Party(int p, char name) {
            this.p = p;
            this.name = name;
        }

        int getP() {
            return p;
        }

        char getName() {
            return name;
        }

        void dec() {
            p--;
        }
    }
}
