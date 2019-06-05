package Cards;

public class Card {

        public int card_id;
        public String name;
        public String position;
        public String remark;
        public int sort;
        public String owner;

        public Card(int i){
            card_id=i;
            name = "name" + String.valueOf(card_id);
            position="";
            remark="new gen card";
            sort=0;
            owner="";
        }
}
