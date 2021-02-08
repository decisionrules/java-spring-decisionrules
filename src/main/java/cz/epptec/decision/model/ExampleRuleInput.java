package cz.epptec.decision.model;

public class ExampleRuleInput {
    private Delivery delivery;
    private Pack pack;

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(final Delivery delivery) {
        this.delivery = delivery;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(final Pack pack) {
        this.pack = pack;
    }

    public static class Delivery {
        private int distance;
        private String tariff;

        public int getDistance() {
            return distance;
        }

        public void setDistance(final int distance) {
            this.distance = distance;
        }

        public String getTariff() {
            return tariff;
        }

        public void setTariff(final String tariff) {
            this.tariff = tariff;
        }
    }

    public static class Pack {
        private int weight;
        private int longestSide;

        public int getWeight() {
            return weight;
        }

        public void setWeight(final int weight) {
            this.weight = weight;
        }

        public int getLongestSide() {
            return longestSide;
        }

        public void setLongestSide(final int longestSide) {
            this.longestSide = longestSide;
        }
    }
}
