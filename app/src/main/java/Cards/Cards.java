package Cards;

public class Cards {

    public  int count;
    public  Card[] allCard;
    public  String msg;


    //为卡堆生成N张卡
       public  Cards(int j){
        int i ;

        this.msg="new";
        this.count=j;
        allCard = new Card[count];

        for(i=0;i<count;i++){
            allCard[i]=new Card(i);
        }
    }

    //洗牌
    public void shuffle(){

        int i ;
        int j ;
        int[] tmpSort;
        boolean BolIsUnique;

        tmpSort = new int[allCard.length];

        //为每张卡的sort生成一个 N*100 的随机数
        for(i=0;i<allCard.length;i++){
            allCard[i].sort=(int)(allCard.length * Math.random() * 100);
        }

        //用另一个数组记录上面的每个随机数从小到大的排位
        for(i=0;i<allCard.length;i++){
            tmpSort[i]=0;
            for(j=0;j<allCard.length;j++){
                if (allCard[i].sort>allCard[j].sort){
                    tmpSort[i]++;
                }
            }
        }

        //将sort随机数替换成排位数
        for(i=0;i<allCard.length;i++){
            allCard[i].sort=tmpSort[i];
        }

        //重新排列卡组
        shuffle_bySort();
    }

    //根据sort排位数将卡牌数组重新排位
    public void shuffle_bySort(){

        int i ;

        //复制一个数组
        Card[] allCard_Copy;
        allCard_Copy=allCard.clone();

        //将复制卡组  按sort顺重新设定到原卡组
        for(i=0;i<allCard_Copy.length;i++){
            allCard[allCard_Copy[i].sort]=allCard_Copy[i];
        }
    }

}
