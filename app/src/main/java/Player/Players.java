package Player;

import Cards.Card;

public class Players {

    public  int count;
    public  Player[] allPlayer;
    public  String msg;


    //为玩家群生成N个玩家
    public  Players(int j){
        int i ;

        this.msg="new";
        this.count=j;
        allPlayer = new Player[count];

        for(i=0;i<count;i++){
            allPlayer[i]=new Player(i);
        }
    }
}
